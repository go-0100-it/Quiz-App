package com.example.android.car_knowledge_quiz_project3.carknowledgequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int questionNumber;
    public int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionNumber = 0;
        correctAnswers = 0;
        setImage();
        setText();
        setViewVisibility();

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollView scrollView = (ScrollView)findViewById(R.id.scroll_view);
                if (questionNumber > 0 && questionNumber < 10) {
                    getUserInput();
                    questionNumber = questionNumber + 1;
                    setImage();
                    setText();
                    setViewVisibility();
                    scrollView.smoothScrollTo(0, 0);
                }else{
                    switch (questionNumber){
                        case 0:
                            questionNumber = questionNumber + 1;
                            setImage();
                            setText();
                            setViewVisibility();
                            scrollView.smoothScrollTo(0, 0);
                            break;
                        case 10:
                            getUserInput();
                            toaster("You correctly answered " +correctAnswers+ " out of 10 questions.");
                            questionNumber = questionNumber + 1;
                            setImage();
                            setText();
                            setViewVisibility();
                            scrollView.smoothScrollTo(0, 0);
                            break;
                        case 11:
                            questionNumber = 0;
                            setImage();
                            setText();
                            setViewVisibility();
                            correctAnswers = 0;
                            scrollView.smoothScrollTo(0, 0);
                            break;
                        default:
                            break;
                    }
                }
                scrollView.smoothScrollTo(0,0);
            }
        });
    }
    /*
    * Setting unique text to TextViews determined by Question number.
    * Changing of text, images, and view visibility allows for a single view app to appear as multi view.
    * Retrieving strings from string arrays stored in resources using question number as the index.
    */
    /*
    Setting unique image determined by Question number.
    */
    public void setImage() {
        ImageView img = (ImageView) findViewById(R.id.question_image);
        switch (questionNumber) {
            case 0:
                img.setImageResource(R.drawable.begin);
                break;
            case 1:
                img.setImageResource(R.drawable.pressure);
                break;
            case 2:
                img.setImageResource(R.drawable.piston);
                break;
            case 3:
                img.setImageResource(R.drawable.underthehood);
                break;
            case 4:
                img.setImageResource(R.drawable.underthehood);
                break;
            case 5:
                img.setImageResource(R.drawable.brake_rotor);
                break;
            case 6:
                img.setImageResource(R.drawable.fuel_gauge);
                break;
            case 7:
                img.setImageResource(R.drawable.alternator);
                break;
            case 8:
                img.setImageResource(R.drawable.brakes);
                break;
            case 9:
                img.setImageResource(R.drawable.exhaust);
                break;
            case 10:
                img.setImageResource(R.drawable.routine_maintenance);
                break;
            case 11:
                img.setImageResource(R.drawable.finish);
                break;
            default:
                break;
        }
    }
    /*
    Setting unique text to TextViews determined by Question number.
    */
    public void setText() {
        TextView questionNumberTxt = (TextView) findViewById(R.id.question_number_txt);
        TextView questionTxt = (TextView) findViewById(R.id.question_txt);
        TextView instructionTxt = (TextView) findViewById(R.id.instruction_txt);
        Button nextButton = (Button) findViewById(R.id.next_button);
        String[] questionNumberString = getResources().getStringArray(R.array.question_number_list);
        questionNumberTxt.setText(questionNumberString[questionNumber]);
        String[] questions = getResources().getStringArray(R.array.question_list);
        questionTxt.setText(questions[questionNumber]);
        String[] instructions = getResources().getStringArray(R.array.instruction_list);
        instructionTxt.setText(instructions[questionNumber]);
        if (questionNumber == 0) {
            nextButton.setText(getResources().getText(R.string.begin_button_text));
        } else if (questionNumber == 10) {
            nextButton.setText(getResources().getText(R.string.finish_button_text));
        } else if (questionNumber == 11) {
            String instructionMessage = instructions[11]+" "+correctAnswers+" "+instructions[12];
            instructionTxt.setText(instructionMessage);
            nextButton.setText(getResources().getText(R.string.try_again_button_text));
        } else {
            nextButton.setText(getResources().getText(R.string.next_button_text));
        }
        TextView option1;
        TextView option2;
        TextView option3;
        TextView option4;
        String[] option1List = getResources().getStringArray(R.array.option1_list);
        String[] option2List = getResources().getStringArray(R.array.option2_list);
        String[] option3List = getResources().getStringArray(R.array.option3_list);
        String[] option4List = getResources().getStringArray(R.array.option4_list);
        if (questionNumber == 1 || questionNumber == 2 || questionNumber == 3 || questionNumber == 6 || questionNumber == 8) {
            option1 = (TextView) findViewById(R.id.radio_btn1);
            option2 = (TextView) findViewById(R.id.radio_btn2);
            option3 = (TextView) findViewById(R.id.radio_btn3);
            option4 = (TextView) findViewById(R.id.radio_btn4);
        } else {
            option1 = (TextView) findViewById(R.id.check_box1);
            option2 = (TextView) findViewById(R.id.check_box2);
            option3 = (TextView) findViewById(R.id.check_box3);
            option4 = (TextView) findViewById(R.id.check_box4);
        }
        option1.setText(option1List[questionNumber]);
        option2.setText(option2List[questionNumber]);
        option3.setText(option3List[questionNumber]);
        option4.setText(option4List[questionNumber]);
    }
    /*
    Setting visibility of views enables varying of answer input types.
    CheckBox - multi answer input
    RadioButton - single answer input
    EditText - user typed input
    Visiblility is determined by question number in the code below
    */
    public void setViewVisibility() {
        RelativeLayout checkBoxLayout = (RelativeLayout) findViewById(R.id.checkbox_layout);
        LinearLayout editTextLayout = (LinearLayout) findViewById(R.id.edit_text_layout);
        RelativeLayout radioGroupLayout = (RelativeLayout) findViewById(R.id.radio_btn_layout);
        if (questionNumber == 1 || questionNumber == 2 || questionNumber == 3 || questionNumber == 6 || questionNumber == 8) {
            radioGroupLayout.setVisibility(View.VISIBLE);
            checkBoxLayout.setVisibility(View.GONE);
            editTextLayout.setVisibility(View.GONE);
        } else if (questionNumber == 4 || questionNumber == 9 || questionNumber == 10) {
            radioGroupLayout.setVisibility(View.GONE);
            checkBoxLayout.setVisibility(View.VISIBLE);
            editTextLayout.setVisibility(View.GONE);
        } else if (questionNumber == 5 || questionNumber == 7) {
            radioGroupLayout.setVisibility(View.GONE);
            checkBoxLayout.setVisibility(View.GONE);
            editTextLayout.setVisibility(View.VISIBLE);
        } else {
            radioGroupLayout.setVisibility(View.GONE);
            checkBoxLayout.setVisibility(View.GONE);
            editTextLayout.setVisibility(View.GONE);
        }
    }
    /*
    * Getting text of item selected then calling method to check for correctness
    * Used a separate method for checking correctness reduces code repetition
    */
    public void getUserInput() {
        String ans = "";
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        if (questionNumber == 1 || questionNumber == 2 || questionNumber == 3 || questionNumber == 6 || questionNumber == 8) {
            RadioButton radioButton1 = (RadioButton) findViewById(R.id.radio_btn1);
            RadioButton radioButton2 = (RadioButton) findViewById(R.id.radio_btn2);
            RadioButton radioButton3 = (RadioButton) findViewById(R.id.radio_btn3);
            RadioButton radioButton4 = (RadioButton) findViewById(R.id.radio_btn4);
            if(radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked()){
                if (radioButton1.isChecked()) {
                    ans = radioButton1.getText().toString();
                }else if (radioButton2.isChecked()) {
                    ans = radioButton2.getText().toString();
                }else if (radioButton3.isChecked()) {
                    ans = radioButton3.getText().toString();
                }else {
                    ans = radioButton4.getText().toString();
                }
                checkForCorrectness(ans);
            } else {
                toaster(getString(R.string.incorrect_toast));
            }
            radioGroup.clearCheck();
        }
        if (questionNumber == 4 || questionNumber == 9 || questionNumber == 10) {
            CheckBox checkBox1 = (CheckBox) findViewById(R.id.check_box1);
            CheckBox checkBox2 = (CheckBox) findViewById(R.id.check_box2);
            CheckBox checkBox3 = (CheckBox) findViewById(R.id.check_box3);
            CheckBox checkBox4 = (CheckBox) findViewById(R.id.check_box4);
            if(!checkBox1.isChecked()&&!checkBox2.isChecked()&&!checkBox3.isChecked()&&!checkBox4.isChecked()){
                toaster(getString(R.string.incorrect_toast));
            }else {
                if (checkBox1.isChecked()) {
                    ans = ans + checkBox1.getText().toString();
                    checkBox1.setChecked(false);
                }
                if (checkBox2.isChecked()) {
                    ans = ans + checkBox2.getText().toString();
                    checkBox2.setChecked(false);
                }
                if (checkBox3.isChecked()) {
                    ans = ans + checkBox3.getText().toString();
                    checkBox3.setChecked(false);
                }
                if (checkBox4.isChecked()) {
                    ans = ans + checkBox4.getText().toString();
                    checkBox4.setChecked(false);
                }
                checkForCorrectness(ans);
            }
        }
        if (questionNumber == 5 || questionNumber == 7) {
            EditText editText = (EditText) findViewById(R.id.answer_edit_txt);
            String answerInputted = editText.getText().toString();
            editText.setText("");
            checkForCorrectness(answerInputted);
        }
    }
    /*
    *  Using question number to look-up correct answer string in answer string-array and comparing with the user selected string or inputted string.
     */
    public void checkForCorrectness(String answersSelected) {
        String[] correctAnswerList = getResources().getStringArray(R.array.answer_list);
        if(answersSelected.equals(correctAnswerList[questionNumber])) {
            correctAnswers = correctAnswers + 1;
            toaster(getString(R.string.correct_toast));
        }else{
            toaster(getString(R.string.incorrect_toast));
        }
    }
    public void toaster(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
