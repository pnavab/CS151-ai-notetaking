# CS151-ai-notetaking

# 1
## Team 10: Pablo Nava Barrera, Jacob Kavanal, Yannis Shpits, Joshua Demo

### Problem
There are probably questions that you keep Googling over and over every time you need the answer because you just can't remember it. Having one place to store all this info would be great, especially if you didn't even have to leave that tab to find the answers. Even for normal note-taking, opening a new tab and parsing through the available answers can be time-consuming, even if it's a new tab for asking ChatGPT. If only there was one place to take notes and ask ChatGPT directly, without ever having to leave that website...

### Functionality
This web based notetaking applet will have AI integrated into it so the user can ask LLMs, such as ChatGPT, in a textbox on the same page, where it will output the answer into the note itself. From there, the user can format the note how they wish and save it locally. The usage should not be for long queries, but rather simple questions that ChatGPT can answer briefly and save the user the time of finding an answer online or even asking ChatGPT in a new tab. 

### Solution
The project will be completed entirely in Java, using the built-in J-Frame for the front end. 

# 2
### Steps to run the code
1. Get your own OpenAI API key from https://platform.openai.com/api-keys. It will cost fractions of a cent to test our project. Enter it into the apiKey String variable in the "Gpt.java" file on line 21.
2. Go to src/main/java/com/example/ainotetakingfx/Root.java and run the file. This will open our GUI.
3. You can now name a note, ask GPT something, and format the note however you want. You can save the note, create new notes, as well as delete any existing notes by opening the note to delete and clicking the Delete button.

# 3
### Our app running
![image](https://github.com/pnavab/CS151-ai-notetaking/assets/114110926/9d23377d-0b32-404b-ab22-56eaba86fd3a)



# 4
### How did we solve the problem?
We solved this problem by utilizing Java's libraries for making an HTTP request. With our OpenAI Key, we were able to query the completion API passing the input from the "query" text field as the body of our prompt. After getting the response, we appended it to the main note's text field. All together, this allows for a fully functional note-taking app that allows users to never have to leave the tab/app in order to get answers. They can create new notes and ask GPT all in one place and save it for later. 

# 5
### Class Diagram
![image](https://github.com/pnavab/CS151-ai-notetaking/assets/114110926/f408dfe0-5fa7-4e8e-8e1b-408e43644a4d)

# 6
### Description
With our AI powered note taking app, any user is able to interact with the GPT artificial intelligence integrated into their very own note. They don't have to open a new tab and go to ChatGPT anymore. It can all be done from the comfort of our Java GUI application, conveniently storing as many notes as the user requires. The entire application's features are available on the main page, with the note title at the top and the main note text field in the center. With the query field under the title, GPT will deliver speedy answers to the user's note. Once saved, a list of all previously saved notes will begin to populate under the note text field. If the user wishes to delete old notes, they can select it from the list and click the delete button at the bottom of the GUI.
