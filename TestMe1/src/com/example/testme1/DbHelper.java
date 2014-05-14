package com.example.testme1;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "triviaQuiz4";
	// tasks table name
	private static final String TABLE_QUEST = "quest4";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_CAT = "cat";
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private SQLiteDatabase dbase;
	private String CAT;
	public DbHelper(Context context, String cAT) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		CAT=cAT; 
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		System.out.println("************************BEFORE DROP**************");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		System.out.println("************************AFTER DROP, BEFORE CREATION**************");
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_CAT+" TEXT)";
		db.execSQL(sql);	
		System.out.println("************************AFTER CREATION**************");
		
		addQuestions();
		//db.close();
	}
	private void addQuestions()
	{
		//MATH QUESTIONS
		Question q1=new Question("What is 1+1?","3", "2", "7", "2","MATH");
		this.addQuestion(q1);
		Question q2=new Question("What is 7*5?", "12", "15", "35", "35","MATH");
		this.addQuestion(q2);
		Question q3=new Question("What is 4/2?","2", "6","8","2","MATH");
		this.addQuestion(q3);
		Question q4=new Question("What is the square root of 25?",	"3", "5", "7","5","MATH");
		this.addQuestion(q4);
		Question q5=new Question("What is 2^2?","6","2","4","4","MATH");
		this.addQuestion(q5);
		
		//SCIENCE QUESTIONS
		Question q6=new Question("Brass gets discoloured in air because of the presence of which of the following gases in air?","Oxygen", "Hydrogen sulphide", "Carbon Dioxide", "Hydrogen sulphide","SCIENCE");
		this.addQuestion(q6);
		Question q7=new Question("Which of the following is a non metal that remains liquid at room temperature?", "Phosphorous", "Bromine", "Chlorine", "Bromine","SCIENCE");
		this.addQuestion(q7);
		Question q8=new Question("Chlorophyll is a naturally occurring chelate compound in which central metal is:","calcium", "copper","magnesium","magnesium","SCIENCE");
		this.addQuestion(q8);
		Question q9=new Question("Which of the following is used in pencils?",	"Graphite", "Phosphorous", "Charcoal","Graphite","SCIENCE");
		this.addQuestion(q9);
		Question q10=new Question("Which of the following metals forms an amalgam with other metals?","Tin","Lead","Mercury","Mercury","SCIENCE");
		this.addQuestion(q10);
		
		//HISTORY QUESTIONS
		Question q16=new Question("What year did the United States celebrate its bicentennial?","1976", "1977", "1978", "1976","HISTORY");
		this.addQuestion(q16);
		Question q17=new Question("Who is traditionally known as \"The Father of the United States Constitution\"?", "George Washington", "John Adams", "James Madison", "James Madison","HISTORY");
		this.addQuestion(q17);
		Question q18=new Question("Who has traditionally been given credit for sewing the first American flag?","John Hancock", "Betsy Ross","Franklin Roosevelt","Betsy Ross","HISTORY");
		this.addQuestion(q18);
		Question q19=new Question("Where is the Bay of Pigs?",	"Cuba", "Detroit", "Tunisia","Cuba","HISTORY");
		this.addQuestion(q19);
		Question q20=new Question("When the eagle was chosen as the national symbol, Benjamin Franklin suggested another animal would be more appropriate. What animal was it?","Seal","Lion","Turkey","Turkey","HISTORY");
		this.addQuestion(q20);
		
		//POLITICS QUESTIONS
		Question q11=new Question("What is the supreme law of the land?","The President", "The Parliment", "The Constitution", "The Constitution","POLITICS");
		this.addQuestion(q11);
		Question q12=new Question("What do we call the first ten amendments to the Constitution?", "Los Dies", "Bill of Dimes", "Bill of Rights", "Bill of Rights","POLITICS");
		this.addQuestion(q12);
		Question q13=new Question("How many amendments does the Constitution have?","17", "27","10","27","POLITICS");
		this.addQuestion(q13);
		Question q14=new Question("Who is in charge of the executive branch?",	"The President", "Congress", "The Electoral College","The President","POLITICS");
		this.addQuestion(q14);
		Question q15=new Question("How many U.S. Senators are there?","100","50","87","100","POLITICS");
		this.addQuestion(q15);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		values.put(KEY_CAT, quest.getCAT());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);		
	}
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST +" WHERE cat = '"+CAT+"'";
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quest.setCAT(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}
