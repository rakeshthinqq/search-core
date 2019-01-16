SoFi Backend Challenge
============================

## Intro

Welcome! This challenge is designed to give us a better idea of how you work
on a back-end project. We've found project-based challenges like this are
often more accurate than traditional coding interviews.

You're free to take as much time as you like, but you should **expect to spend 2 to 4 hours on this challenge** depending on how familiar you are with your project setup.

## The Challenge

At Sofi, we've decided to build some internal "portals" that make our
mortgage staff more effective. Lately, we realized that GIFs shared per hour
is really the most important metric so we've decided to build an internal portal
for sharing GIF collections! It's like... Pinterest for GIFs?
Well.. you get the idea.

Your quest is to complete the **two requirements sections** below.

When you're done, check out the [submission guidelines](#submitting).

When you come for the onsite interview, you will get to extend your submission with a new feature, so keep that in mind when you're working on it.

Best of luck!

### Requirements - PART 1

- Create a GIF search web service
- It should have an HTTP GET API with the path `/search/[search term]`
- It should run on port 8080
- Leverage the [Giphy API](https://github.com/giphy/GiphyAPI) and use the Giphy public beta key
- Always return exactly 5 results or 0 results if there are less than 5 available
- Responses should be JSON in the following format:

```json
{
  "data": [
    {
      "gif_id": "FiGiRei2ICzzG",
      "url": "http://giphy.com/gifs/funny-cat-FiGiRei2ICzzG",
    }
  ]
}
```

### Requirements - PART 2
- For this section, dockerize your application.
  - Complete Dockerfile
  - After we build your dockerfile and run the image, your service should be able to repond to API requests.

## Submitting

1. Clone this repo and commit your code as you work. When you are satisfied with your work, follow these instructions to submit:
2. `git format-patch master --stdout > your-name.patch`.
    Or, if you worked straight off of master, use the commit sha preceding
    your work.
3. Email the patch to [klochner@sofi.com](mailto:klochner@sofi.com).

## Evaluation

- We're going to be evaluating your project on the following criteria
  - Did you meet all of the requirements?
  - Can we run the application as a docker container?
  - How easy is it for someone new to your project to understand your code?
  - How well are you using the technology ecosystem of your choice?
  - We will review design decisions, the application structure, SOLID principles, clean code, any technologies or frameworks used.

## Feedback

We're always looking for ways to improve our processes at Sofi so
let us know if anything is especially frustrating (or fun)!
