package com.yournews.yournews;
public class Diary {
    private String diaryId;
    private String UName;
    private String Diary;
    private String date;
    /**
     *
     */
    public Diary(){
    }

    public Diary(String diaryId, String UName, String Diary,String Date) {
        this.diaryId = diaryId;
        this.UName = UName;
        this.Diary = Diary;
        this.date = Date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public void setDiary(String diary) {
        Diary = diary;
    }
    public String getDiaryId() {
        return diaryId;
    }
    public String getUName() {
        return UName;
    }
    public String getDiary() {
        return Diary;
    }
}
