# CS151-ai-notetaking
## Team 10: Pablo Nava Barrera, Jacob Kavanal, Yannis Shpits, Joshua Demo

### Problem
There are probably questions that you keep Googling over and over every time you need the answer because you just can't remember it. Having one place to store all this info would be great, especially if you didn't even have to leave that tab to find the answers. Even for normal note-taking, opening a new tab and parsing through the available answers can be time-consuming, even if it's a new tab for asking ChatGPT. If only there was one place to take notes and ask ChatGPT directly, without ever having to leave that website...

### Functionality
This web based notetaking applet will have AI integrated into it so the user can ask LLMs such as ChatGPT in a textbox on the same page, where it will output the answer into the note itself. From there, the user can format the note how they wish and save it to a database that stores the notes. The usage should not be for long queries, but rather simple questions that ChatGPT can answer briefly and save the user the time of finding an answer online or even asking ChatGPT in a new tab. 

### Solution
The main notetaking functionality will be made using a React frontend, Express backend, and MongoDB for the database. Users will be able to perform CRUD operations on their notes from the frontend. For the AI functionality, we will use the OpenAI API [[1]](https://platform.openai.com/docs/api-reference) in the backend based on the user's prompt. 
