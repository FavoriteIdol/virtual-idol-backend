package com.outsider.virtual.file.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String imageName;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String ttsFilename;

    private String emotion;

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getTtsFilename() {
        return ttsFilename;
    }

    public void setTtsFilename(String ttsFilename) {
        this.ttsFilename = ttsFilename;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String analysis;
    private String mnStatus;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getMnStatus() {
        return mnStatus;
    }

    public void setMnStatus(String mnStatus) {
        this.mnStatus = mnStatus;
    }
}