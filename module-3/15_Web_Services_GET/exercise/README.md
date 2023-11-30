# Exercises for Web API (GET)

In this exercise, you'll build an application that displays topics and messages for a message board application. Most of the application is already complete. Before beginning, review the updated starter code. Your task is to implement a new service object to connect to the web API. Then, you'll use that service in the view component to get data from the API and pass it to the other components using props. 

## Before you begin

Run the command `npm install` in the project directory to install any dependencies. 

Then run the project using the command `npm run dev`. Initially, you only see a loading message displayed. The application doesn't function correctly until you provide services to retrieve the data.

To test the project and verify completion, you can run the tests using either of the following commands:

* `npm run test:e2e-headless` - This runs the tests in "headless" mode to display the results in the console. Tests run significantly faster this way, but you don't get the additional support of the Cypress UI.
* `npm run test:e2e` - This runs the tests using the Cypress user interface, which may provide extra help, like screenshots, when troubleshooting a failed test. 

Note: The application and tests can't run simultaneously. If the application is already running, you may see the tests fail with the following message:

```bash
ERROR: "json-server-test" exited with 1.
Error: server closed unexpectedly
```

If this occurs, stop the application in the terminal or run `npx kill-port 3000`, then try to run the tests again.


## Step One: Get the topic list
When first running the application, you'll see only a loading message. To fix this, you must create a service to get data from the API. Start by using Postman to make a `GET` request to `/topics` and verify the service endpoint works.

Next, create a new `src/services` folder and a new file called `TopicService.js`. This represents the Topics Web API. Set the base URL to `http://localhost:3000`. Add a `list` method that performs a `GET` request to the URL `/topics` and returns a `Promise`. 

In the `HomeView` locate the `getTopics` method and the **TODO** for retrieving the data. Update the code to call the new `list` method from the `TopicService`. 

- On success, set `isLoading` to false and `topics` to the array from the response. 
- If there are errors, call the existing `handleErrorResponse` method.

Since there's already a prop called `topics` in the `TopicList` component, the Topics list now displays correctly.

After this step is complete, verify the `Step One Tests` tests pass.

## Step Two: Get a topic details

When running the application, if you click a topic from the Home page, the view changes but never stops loading. In this step, you'll correct that behavior.

Use Postman to make a `GET` request to `/topics/:id` and verify the service endpoint works. Add a new `get` method to the `TopicService` that takes a topic ID as a parameter, performs a `GET` request to the URL `/topics/:id`, and returns a `Promise`. 

In the `TopicDetailsView` locate the `getTopic` method and the **TODO** for retrieving the data. Update the code to call the new `get` method from the `TopicService`. 

- On success, set `isLoading` to false and `topic` to the object from the response. 
- If there are errors, call the existing `handleErrorResponse` method, passing in the error object. 

Since there's already a prop called `topic` in the `TopicDetails` component, the data now correctly displays when you click a topic from the Topics list.

After this step is complete, verify the `Step Two Tests` tests pass.

## Step Three: Get message details

When running the application, if you click a message from the Topic Details, the view changes but never stops loading. In this step, you'll correct that behavior.

Use Postman to make a `GET` request to `/messages/:id` and verify the service endpoint works. 

Then create a file for the `MessageService` to represent the Messages Web API. Set the base URL to `http://localhost:3000`. Add a `get` method that takes a message ID as a parameter, performs a `GET` request to the URL `/messages/:id`, and returns a `Promise`. 

In the `MessageDetailsView` locate the `getMessage` method and the **TODO** for retrieving the data. Update the code to call the new `get` method from the `MessageService`. 

- On success, set `isLoading` to false and set the `message` property to the object from the response.
- If there are errors, call the existing `handleErrorResponse` method, passing in the error object. 

Since there's already a prop called `message` in the `MessageDetails` component, the data now correctly displays when you click a message from the Messages list.

After this step is complete, verify the `Step Three Tests` tests pass.

The application is now fully functional, and all tests pass.