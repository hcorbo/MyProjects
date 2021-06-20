import 'package:flutter/material.dart';

import 'package:quiz_app/widgets/question.dart';
import '../models/question.dart' as modelQuestion;
import 'answer.dart';

class Quiz extends StatelessWidget {
  //List<Map<String, Object>> questions;
  List<modelQuestion.Question> questions;
  int questionIndex;
  Function answerQuestion;

  Quiz({
    @required this.questions,
    @required this.questionIndex,
    @required this.answerQuestion,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Question(questions[questionIndex].questionText),
        Expanded(
          child: ListView.builder(
            itemCount: questions[questionIndex].answers.length,
            itemBuilder: (BuildContext buildContext, int index) {
              return Answer(
                  () => answerQuestion(
                      questions[questionIndex].answers[index].score),
                  questions[questionIndex].answers[index].text);
            },
          ),
        )
      ],
    );

    // return Column(
    //   children: [
    //     Question(questions[questionIndex]['questionText']),
    // // Spread ... operator jer children prima listu Widgeta, a ne listu aswer-a
    //     ...(questions[questionIndex]['answers'] as List<Map<String, Object>>)
    //         .map((answer) {
    //       return Answer(() => answerQuestion(answer['score']), answer['text']);
    //     }).toList(),
    //   ],
    // );
  }
}
