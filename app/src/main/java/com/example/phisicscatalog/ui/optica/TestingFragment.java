package com.example.phisicscatalog.ui.optica;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.phisicscatalog.R;
import com.example.phisicscatalog.parserXml.QuestionsInfoModel;
import com.example.phisicscatalog.parserXml.QuestionsXMLParser;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class TestingFragment extends Fragment {
    private static final String NAME_OF_XML_FILE = "quesrtions_optica.xml";

    ListView questionsOpticaList;
    int count = 0;

    public TestingFragment() {
        super(R.layout.optica_test_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionsOpticaList = view.findViewById(R.id.quetions_optica_list);

        QuestionsXMLParser xmlParser = new QuestionsXMLParser(NAME_OF_XML_FILE);
        List<QuestionsInfoModel> questionDataListsFromXML = null;
        try {
            questionDataListsFromXML = xmlParser.parse(getContext());
        } catch (
                ParserConfigurationException e) {
            Log.d("list", "!!!!!!!!!ParserConfigurationException - проглема тут ");
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
            Log.d("list", "Парсер не нашёл файл");
        } catch (
                SAXException e) {
            Log.d("list", "!!!!!!!SEXExeption - роблема тут");
            e.printStackTrace();
        }

        String[] questions = new String[questionDataListsFromXML.size()];
        int[] correctAnswer = new int[questionDataListsFromXML.size()];

        for (int i = 0; i < questions.length; i++) {
            questions[i] = questionDataListsFromXML.get(i).getQuestion();
            correctAnswer[i] = questionDataListsFromXML.get(i).getRightAnswer();
        }

        for(QuestionsInfoModel el: questionDataListsFromXML){
            Log.d("list", el.getQuestion());
            Log.d("list", String.valueOf( el.getRightAnswer()));
            List<String> answerList = el.getAnswerList();
            Log.d("list", "size of list" + answerList.size());
            for(String str : answerList){

                Log.d("list", str);
            }

        }

        MyAdapterForQuestionBlock adapter = new MyAdapterForQuestionBlock(getContext(), questionDataListsFromXML);
        questionsOpticaList.setAdapter(adapter);

        //логика обработки правильных ответов
    }

    private class MyAdapterForQuestionBlock extends ArrayAdapter<QuestionsInfoModel> {
        private Context context;
        private List<QuestionsInfoModel> listOfQuestionsBlock;

        public MyAdapterForQuestionBlock(@NonNull Context context, List<QuestionsInfoModel> listOfQuestionsBlock) {
            super(context, R.layout.custom_item_question_block, listOfQuestionsBlock);
            this.context = context;
            this.listOfQuestionsBlock = listOfQuestionsBlock;
            Log.d("list", "Создался мой адаптер");
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customView = layoutInflater.inflate(R.layout.custom_item_question_block, parent, false);

            TextView questionText = customView.findViewById(R.id.text_of_question);
            questionText.setText(listOfQuestionsBlock.get(position).getQuestion());
            Log.d("list", "---Question " + questionText.getText());
            List<String> answerList = listOfQuestionsBlock.get(position).getAnswerList();

            RadioButton radioButton1 = customView.findViewById(R.id.answer_1);
            radioButton1.setText(answerList.get(0));
            radioButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfQuestionsBlock.get(position). userAnswer = 1;
                }
            });
            RadioButton radioButton2 = customView.findViewById(R.id.answer_2);
            radioButton2.setText(answerList.get(1));
            radioButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfQuestionsBlock.get(position). userAnswer = 2;
                }
            });
            RadioButton radioButton3 = customView.findViewById(R.id.answer_3);
            radioButton3.setText(answerList.get(2));
            radioButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfQuestionsBlock.get(position). userAnswer = 3;
                }
            });
            RadioButton radioButton4 = customView.findViewById(R.id.answer_4);
            radioButton4.setText(answerList.get(3));
            radioButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfQuestionsBlock.get(position). userAnswer = 4;
                }
            });
            return customView;
        }
    }
}
