Expedite Front-end Challenge
============================

## Intro

Welcome! This challenge is designed to give us a better idea of how you work
on a back-end project. We've found project-based challenges like this are
often more accurate than traditional coding interviews.

You should **expect to spend 2 hours on this challenge**.

## The Challenge

At Clara, we've decided to build some internal "portals" that make our
mortgage staff more effective. Lately, we realized that GIFs shared per hour
is really the most important metric so we've decided to build an internal portal
for sharing GIF collections! It's like... Pinterest for GIFs?
Well.. you get the idea.

Your quest is to complete the **Basic Requirements** and
your choice of **2 Features/Enhancements** from the list below.

When you're done, check out the [submission guidelines](#submitting).

Best of luck!

### Requirements

- Create a GIF search API that leverages the [Giphy API](https://github.com/giphy/GiphyAPI)
- Use the Giphy public beta key
- Your HTTP endpoint should have the path `/search/[search term]`
- Always return exactly 5 results or 0 if there are less than 5 available
- Responses should be JSON in the following format:
 
    {

        data: [
            {
        		  	gif_id: "FiGiRei2ICzzG",
        				url: "http://giphy.com/gifs/funny-cat-FiGiRei2ICzzG",
            }
        ]
    }
				


### Advanced Features (pick one)
- 

## Coding

1. Clone this repo and commit your code here

## Submitting

When you are satisfied with your work, follow these instructions to submit:

1. `git format-patch master --stdout > your-name.patch`.
    Or, if you worked straight off of master, use the commit sha preceding
    your work.
2. Email the patch to [recruiting+challenge-back-end@clara.com](mailto:recruiting+challenge-back-end@clara.com).

## Feedback

We're always looking for ways to improve our processes at Clara so
let us know if anything is especially frustrating (or fun)!
