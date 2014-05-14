package com.example.testme1;
public class Question {
	private int ID;
	private String QUESTION;
	private String OPTA;
	private String OPTB;
	private String OPTC;
	private String ANSWER,CAT;
	public Question()
	{
		ID=0;
		QUESTION="";
		OPTA="";
		OPTB="";
		OPTC="";
		ANSWER="";
		CAT="";
	}
	public Question(String qUESTION, String oPTA, String oPTB, String oPTC,
			String aNSWER, String cAT) {
		
		QUESTION = qUESTION;
		OPTA = oPTA;
		OPTB = oPTB;
		OPTC = oPTC;
		ANSWER = aNSWER;
		CAT=cAT;
	}
	public int getID()
	{
		return ID;
	}
	public String getQUESTION() {
		return QUESTION;
	}
	public String getOPTA() {
		return OPTA;
	}
	public String getOPTB() {
		return OPTB;
	}
	public String getOPTC() {
		return OPTC;
	}
	public String getANSWER() {
		return ANSWER;
	}
	public String getCAT(){
		return CAT;
	}
	public void setCAT(String cAT){
		CAT=cAT;
	}
	public void setID(int id)
	{
		ID=id;
	}
	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}
	public void setOPTA(String oPTA) {
		OPTA = oPTA;
	}
	public void setOPTB(String oPTB) {
		OPTB = oPTB;
	}
	public void setOPTC(String oPTC) {
		OPTC = oPTC;
	}
	public void setANSWER(String aNSWER) {
		ANSWER = aNSWER;
	}
	
}
