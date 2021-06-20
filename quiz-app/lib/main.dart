//One screen app
import 'package:flutter/material.dart';

import './models/question.dart' as modelQuestion;
import './models/answer.dart' as modelAnswer;
import 'widgets/quiz.dart';
import 'widgets/result.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _MyAppState();
  }
}

class _MyAppState extends State<MyApp> {
  List<modelQuestion.Question> _questions = List();

  @override
  void initState() {
    _questions.add(
      modelQuestion.Question(
          questionText: "What\'s your favorite animal?",
          answers: [
            modelAnswer.Answer(score: 1, text: "Rabit"),
            modelAnswer.Answer(score: 2, text: "Snake"),
            modelAnswer.Answer(score: 3, text: "Elephant"),
            modelAnswer.Answer(score: 4, text: "Lion"),
          ]),
    );
    _questions.add(
      modelQuestion.Question(
          questionText: "What\'s your favorite color?",
          answers: [
            modelAnswer.Answer(score: 1, text: "Red"),
            modelAnswer.Answer(score: 2, text: "Green"),
            modelAnswer.Answer(score: 3, text: "Blue"),
            modelAnswer.Answer(score: 4, text: "Yellow"),
          ]),
    );
    _questions.add(
      modelQuestion.Question(
          questionText: "What\'s your favorite time of the year?",
          answers: [
            modelAnswer.Answer(score: 1, text: "Spring"),
            modelAnswer.Answer(score: 2, text: "Summer"),
            modelAnswer.Answer(score: 3, text: "Winter"),
            modelAnswer.Answer(score: 4, text: "Autumn"),
          ]),
    );
    super.initState();
  }

  var _questionIndex = 0;
  var _totalScore = 0;

  void _answerQuestion(int score) {
    _totalScore += score;

    setState(() {
      _questionIndex = _questionIndex + 1;
    });
    print("index: $_questionIndex");
    print("score: $_totalScore");
  }

  void _resetQuiz() {
    setState(() {
      _questionIndex = 0;
      _totalScore = 0;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text(
            'Quiz app',
          ),
        ),
        body: _questionIndex < _questions.length
            ? Quiz(
                questions: _questions,
                questionIndex: _questionIndex,
                answerQuestion: _answerQuestion,
              )
            : Result(
                resetHandler: _resetQuiz,
                resultScore: _totalScore,
              ),
      ),
      theme: ThemeData(primaryColor: Colors.amber),
    );
  }
}
