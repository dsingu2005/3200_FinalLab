# Art Quiz App

## Documentation
For detailed explanations and diagrams, please refer to the [document](https://docs.google.com/document/d/1g58N2rzdGL1B0RacvWGpp4j-rfj1_Urb7-pY-utdyug/edit?usp=sharing).

## Overview
The Art Quiz App is a fun and educational application designed to test users' knowledge of art titles. The app's main functionality revolves around presenting users with questions about art titles retrieved from the Metropolitan Museum of Art's API and scoring their responses.

## Strategy and Functionality
- **MainActivity**: Serving as the entry point, MainActivity orchestrates the user interface setup and manages the game logic.
- **UI States**: The UI comprises various states, including inputting the number of questions, displaying questions and answers, and presenting the final score.
- **loadNextQuestion**: This function fetches two random art objects via API calls, generating IDs within a specified range.
- **generateQuestion**: Utilized to create questions based on metadata from the fetched art objects, prompting users to determine the truthfulness of statements regarding the art titles.
- **Score Tracking**: User scores are monitored and exhibited, incrementing upon correct answers.
- **Game End**: The game concludes once all questions are answered, showcasing the final score.

## Flow Explanation
1. **MainActivity**: As a ComponentActivity, MainActivity acts as the primary gateway.
2. **UI Setup**: The onCreate method initializes the UI via Jetpack Compose, containing a quiz game within a Box.
3. **Question Input**: Users are prompted to input the number of questions they wish to answer.
4. **Question Loading**: Upon submitting the number of questions, the loadNextQuestion method is invoked asynchronously, fetching art objects from the MetApiService interface.
5. **Question Generation**: Questions are generated based on fetched art objects, updating the UI accordingly.
6. **Answering Questions**: Users respond to questions by selecting buttons, with scores adjusted accordingly.
7. **Game Conclusion**: The game ends when all questions are answered, revealing the final score.

## Components
- **ArtObjectView & MainActivityArtObjectView**: These composable functions display art object images, showing loading messages if the object is yet to be fetched.
- **ArtObject & Question**: Data classes representing art objects and quiz questions, respectively.
- **RetrofitInstance**: Instantiates MetApiService to fetch art objects from the Metropolitan Museum of Art's API.
