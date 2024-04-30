#Document for explanations/diagram here:
https://docs.google.com/document/d/1g58N2rzdGL1B0RacvWGpp4j-rfj1_Urb7-pY-utdyug/edit?usp=sharing

A brief description of the strategy and how the app works:
The MainActivity is the entry point of the application. It sets up the UI and handles the game logic.
The UI is composed of several states, including a state for inputting the number of questions, a state for displaying the questions and answers, and a state for displaying the final score.
The loadNextQuestion function is responsible for loading the next question. It does this by making API calls to fetch two random art objects. The IDs for these objects are generated randomly within a specified range.
The generateQuestion function is used to generate a question based on the metadata of the fetched art objects. The question is a statement about the title of the art object, and the user has to guess whether the statement is true or false.
The user's score is tracked and displayed. The score increments when the user selects the correct answer.
The game ends when all questions have been answered, at which point the final score is displayed.

Explanation of Flow:
The application starts with the MainActivity class, which is a ComponentActivity. This is the main entry point of the application.
In the onCreate method of MainActivity, the UI for the application is set using the setContent method. This method uses Jetpack Compose, a modern toolkit for building native Android UI.
The UI consists of a Box that contains a quiz game. The game starts by asking the user to input the number of questions they want to answer.
Once the user submits the number of questions, the loadNextQuestion method is called. This method uses a CoroutineScope to asynchronously fetch two random art objects from the Metropolitan Museum of Art's API using the MetApiService interface.
The loadNextQuestion method then generates a question based on the two fetched art objects and updates the UI with the new question and art object image.
The user can then answer the question by clicking on one of the buttons. If the user's answer is correct, their score is incremented. If the user's answer is incorrect, their score remains the same.
This process repeats until all questions have been answered. Once all questions have been answered, the quiz ends and the user's final score is displayed.
The ArtObjectView and MainActivityArtObjectView composable functions are used to display the art object image in the UI. If the art object is null (i.e., it hasn't been fetched yet), a loading message is displayed instead.
The ArtObject and Question data classes are used to represent an art object and a quiz question, respectively.
The RetrofitInstance object is used to create an instance of MetApiService, which is used to fetch art objects from the Metropolitan Museum of Art's API.
