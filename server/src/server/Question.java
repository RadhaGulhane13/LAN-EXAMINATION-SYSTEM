package server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RADHA
 */
public class Question {

    private String ques;
    private String ans;
    public String opt1;
    public String opt2;
    public String opt3;
    public String opt4;
    private static int count;

    public Question() {

    }

    public Question(String string, String opt1, String opt2, String opt3, String opt4, String string2) {
        // TODO Auto-generated constructor stub
        this.ques = string;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.ans = string2;
        count++;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public static int total_ques() {
        return count;
    }

}
