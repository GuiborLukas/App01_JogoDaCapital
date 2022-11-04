package com.example.app01_jogodacapital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    public int index, correctAnswers, counter, score;
    public int[] pastIndexes;
    public String[] states;
    public HashMap<String, String> capitals;
    public TextView stateView, scoreView, questionView, orientationView, answerFeedbackView;
    public Button next, answerButton, startButton, restartButton, buttonEndGame;
    public EditText capitalInput;
    String currentState, currentCapital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        states = fillStates();
        capitals = fillCapitals();
        initiateView();


    }

    public void initiateView(){
        stateView = findViewById(R.id.textViewStateName);
        scoreView = findViewById(R.id.textViewScore);
        questionView = findViewById(R.id.textViewQuestion);
        orientationView = findViewById(R.id.textViewOrientation);
        answerFeedbackView = findViewById(R.id.textViewAnswerFeedback);
        capitalInput = findViewById(R.id.editTextCapital);
        answerButton = findViewById(R.id.buttonSendAnswer);
        next = findViewById(R.id.buttonNext);
        startButton = findViewById(R.id.buttonStart);
        restartButton = findViewById(R.id.buttonRestart);
        buttonEndGame = findViewById(R.id.buttonEndGame);

        capitalInput.setText("");
        scoreView.setText("");
        answerFeedbackView.setText("");

        stateView.setVisibility(View.GONE);
        scoreView.setVisibility(View.GONE);
        questionView.setVisibility(View.GONE);
        orientationView.setVisibility(View.GONE);
        answerFeedbackView.setVisibility(View.GONE);
        capitalInput.setVisibility(View.GONE);
        answerButton.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);
        buttonEndGame.setVisibility(View.GONE);

        startButton.setVisibility(View.VISIBLE);


    }

    public void startGame(View view){
        stateView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
        orientationView.setVisibility(View.VISIBLE);
        answerFeedbackView.setVisibility(View.VISIBLE);
        capitalInput.setVisibility(View.VISIBLE);
        answerButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.GONE);

        generateRandomIndex();
        showNewState();
        pastIndexes = new int[5];
        correctAnswers = 0;
        counter = 0;
        score = 0;

    }

    public void showNewState(){

            currentState = states[index];
            currentCapital = capitals.get(currentState);
            stateView.setText(currentState);


    }

    public void showResults(View view){
        scoreView.setText("Pontuação total: " + String.valueOf(score * 20) + "pts");
        buttonEndGame.setVisibility(View.GONE);
        stateView.setVisibility(View.GONE);
        questionView.setVisibility(View.GONE);
        orientationView.setVisibility(View.GONE);
        answerFeedbackView.setVisibility(View.GONE);
        capitalInput.setVisibility(View.GONE);
        answerButton.setVisibility(View.GONE);
        startButton.setVisibility(View.GONE);
        restartButton.setVisibility(View.VISIBLE);

    }

    public void restart(View view){
        initiateView();
    }

    public void generateRandomIndex(){
        while(!Arrays.asList(pastIndexes).contains(index)){
           index = new Random().nextInt(27);
           System.out.println(index);
           break;
        }
    }


    public void answer(View view){
        if(capitalInput.length() == 0){
            Toast.makeText(this, "É necessário digitar uma resposta!", Toast.LENGTH_SHORT).show();
        }else {
            counter++;
            answerButton.setVisibility(View.GONE);
            String answer = capitalInput.getText().toString().trim();
            if (answer.equalsIgnoreCase(currentCapital)) {
                answerFeedbackView.setText("Resposta Correta!");
                score++;
                scoreView.setText("Pontuação atual: " + String.valueOf(score * 20) + "pts");
            } else {
                answerFeedbackView.setText("Resposta Incorreta!");
                scoreView.setText("Pontuação atual: " + String.valueOf(score * 20) + "pts");
            }

            if (counter < 5) {
                next.setVisibility(View.VISIBLE);
            }else {
                buttonEndGame.setVisibility(View.VISIBLE);
            }
        }

    }

    public void nextQuestion(View view){
        pastIndexes[counter] = index;
        next.setVisibility(View.GONE);
        answerFeedbackView.setText("");
        capitalInput.setText("");
        generateRandomIndex();
        showNewState();
        answerButton.setVisibility(View.VISIBLE);
    }

    public String[] fillStates(){

        String[] states = {
                "Acre",
                "Alagoas",
                "Amapá",
                "Amazonas",
                "Bahia",
                "Ceará",
                "Distrito Federal",
                "Espírito Santo",
                "Goiás",
                "Maranhão",
                "Mato Grosso",
                "Mato Grosso do Sul",
                "Minas Gerais",
                "Pará",
                "Paraíba",
                "Paraná",
                "Pernambuco",
                "Piauí",
                "Rio de Janeiro",
                "Rio Grande do Norte",
                "Rio Grande do Sul",
                "Rondônia",
                "Roraima",
                "Santa Catarina",
                "São Paulo",
                "Sergipe",
                "Tocantins"
        };

        return states;
    }

    public HashMap<String, String> fillCapitals(){
        HashMap<String, String> capitals = new HashMap<String, String>();
        capitals.put("Acre", "Rio Branco");
        capitals.put("Alagoas", "Maceió");
        capitals.put("Amapá", "Macapá");
        capitals.put("Amazonas", "Manaus");
        capitals.put("Bahia", "Salvador");
        capitals.put("Ceará", "Fortaleza");
        capitals.put("Distrito Federal", "Brasília");
        capitals.put("Espírito Santo", "Vitória");
        capitals.put("Goiás", "Goiânia");
        capitals.put("Maranhão", "São Luís");
        capitals.put("Mato Grosso", "Cuiabá");
        capitals.put("Mato Grosso do Sul", "Campo Grande");
        capitals.put("Minas Gerais", "Belo Horizonte");
        capitals.put("Pará", "Belém");
        capitals.put("Paraíba", "João Pessoa");
        capitals.put("Paraná", "Curitiba");
        capitals.put("Pernambuco", "Recife");
        capitals.put("Piauí", "Teresina");
        capitals.put("Rio de Janeiro", "Rio de Janeiro");
        capitals.put("Rio Grande do Norte", "Natal");
        capitals.put("Rio Grande do Sul", "Porto Alegre");
        capitals.put("Rondônia", "Porto Velho");
        capitals.put("Roraima", "Boa Vista");
        capitals.put("Santa Catarina", "Florianópolis");
        capitals.put("São Paulo", "São Paulo");
        capitals.put("Sergipe", "Aracaju");
        capitals.put("Tocantins", "Palmas");

        return capitals;
    }
}