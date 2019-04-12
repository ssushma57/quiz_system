(function() {

	var app = angular.module("playQuizApp", []);

	var playQuizCtrl = function($scope, $http) {	
		
		$scope.Math = window.Math;
		
		var questions = [];
		var answers = [];
		
		$scope.totalQuestions = 0;
		$scope.questionCount = 0;
		
		$scope.isPlaying = true;
		
		$scope.initialize = function() {
			$scope.playing = true;
		
			$http.get("/questions")
				.then(
					function(response) {
						questions = questions.concat(response.data.content);
						$scope.totalQuestions = questions.length;
						$scope.setQuestion($scope.questionCount);
					}, 
					function(reason) {
						$scope.error = "Could not fetch the data.";
					}
				);
		}
		
		$scope.setQuestion = function(questionNumber) {
			var thisQuestion = questions[questionNumber];
			$scope.currentQuestion = thisQuestion;
			$scope.currentAnswers = thisQuestion.answers;
		}
		
		$scope.answerQuestion = function(selection) {
			if (selection === undefined) {
				alert("Please, choose an answer");
				return;
			}
			
			answers.push({
				questionId: $scope.currentQuestion.id,
				answerId: selection
			});
			
			$scope.questionCount++;
			if ($scope.questionCount == $scope.totalQuestions) {
				$scope.submitAnswers();
			} else {
				$scope.setQuestion($scope.questionCount);
			}	
		}
		
		$scope.submitAnswers = function() {
			$http.post("/submit",
					JSON.stringify(answers))
			.then(
				function(response) {
					$scope.results = response.data;
					$scope.playing = false;
				}, 
				function(reason) {
					$scope.error = "Could not fetch the data.";
				}
			);
		}
	
		$scope.initialize();	
	};

	app.controller("PlayQuizCtrl", ["$scope", "$http", playQuizCtrl]);

}());