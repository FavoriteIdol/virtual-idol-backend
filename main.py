from fastapi import FastAPI, HTTPException, UploadFile, File
from fastapi.responses import JSONResponse, FileResponse
from pydantic import BaseModel
import base64
import requests
from openai import OpenAI
import re
import os
import logging
from dotenv import load_dotenv

# 환경 변수 로드
load_dotenv()
app = FastAPI()

# 로깅 설정
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)
openai_api_key = os.getenv("OPENAI_API_KEY")
if not openai_api_key:
    raise ValueError("OPENAI_API_KEY not found in environment variables")
client = OpenAI(api_key=openai_api_key)

class AnalysisRequest(BaseModel):
    image_url: str

async def get_image_base64(image_url: str) -> str:
    response = requests.get(image_url)
    response.raise_for_status()
    return base64.b64encode(response.content).decode('utf-8')

async def analyze_image_and_chat(image_base64: str):
    psychologist_prompt = """
    당신은 시각적 단서를 통해 인간의 감정과 행동을 분석하는 데 전문성을 갖춘 공감적이고 통찰력 있는 심리학자입니다.
    제공된 이미지를 주의 깊게 살펴보고 전문적인 심리학적 해석을 제공하는 것이 당신의 임무입니다.
    다음 측면에 초점을 맞추세요:
    
    1. 감정 상태: 표현된 주요 감정을 식별하고 설명하세요.
    2. 신체 언어: 자세, 제스처, 표정을 분석하세요.
    3. 환경적 요인: 배경을 고려하고 그것이 어떻게 주체의 심리 상태에 영향을 미칠 수 있는지 설명하세요.
    4. 잠재적인 심리학적 주제: 관련될 수 있는 심리학적 개념이나 문제를 제안하세요.
    5. 치료적 고려사항: 적절한 경우, 부드럽고 지지적인 접근 방식이나 추가 탐구가 필요한 영역을 제안하세요.
    
    존중적이고 비판단적인 어조를 유지하고, 단일 이미지 해석의 한계를 인정하세요.
    귀하의 통찰력을 확정적인 결론이 아닌 전문적인 관찰로 제공하세요.
    
    마지막으로, 이미지에서 감지된 주요 감정을 기쁨, 분노, 슬픔 중 하나로 분류하세요.
    분석 끝에 다음 형식으로 감정 분류를 추가하세요: "감정 분류: [기쁨/분노/슬픔]"
    
    이제 이미지를 한국어로 분석해 주세요.
    """

    messages = [
        {"role": "system", "content": psychologist_prompt},
        {"role": "user", "content": [
            {"type": "image_url", "image_url": {"url": f"data:image/jpeg;base64,{image_base64}"}}
        ]}
    ]

    try:
        response = client.chat.completions.create(
            model="gpt-4o-2024-08-06",  # 요청한 모델 이름
            messages=messages,
            max_tokens=500,
        )
        return response.choices[0].message.content
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

def extract_emotion(analysis: str) -> str:
    match = re.search(r"감정 분류: (기쁨|분노|슬픔)", analysis)
    if match:
        return match.group(1)
    return "감정 분류 불가"

@app.post("/analyze")
async def analyze(request: AnalysisRequest):
    try:
        image_base64 = await get_image_base64(request.image_url)
        analysis = await analyze_image_and_chat(image_base64)
        

        return JSONResponse(content={
            "analysis": analysis
        })
    except requests.RequestException as e:
        return JSONResponse(status_code=400, content={"error": f"이미지 가져오기 실패: {str(e)}"})
    except Exception as e:
        return JSONResponse(status_code=500, content={"error": str(e)})




if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)