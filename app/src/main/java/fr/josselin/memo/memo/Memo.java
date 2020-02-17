package fr.josselin.memo.memo;

/**
 * Created by jos_b on 13/03/2018.
 */

public class Memo {
    private Integer id;
    private String title;
    private String text;

    public Memo(Integer id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getText(){
        return text;
    }

    public void setTitle(String value) {
        title = value;
    }

    public void setText(String value){
        text = value;
    }
}
