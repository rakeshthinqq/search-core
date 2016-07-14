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
- Add a url parameter `p` that specifies the maximum number of bytes in the JSON response
  - A sample request url would like like this: `http://your-server/search/funny+cat?p=2048`
  - If including 5 results would exceed the byte limit, then reduce the number of results so that the response can fit within that limit
  - It's ok to go below the 5 result requirement from above and have 1-4 results
  - If results exist but don't fit within the limit, then return a 500 error
- Add a pagination token to your response in the following format:
 
	    {
	        data: [
	            {
				  	gif_id: "FiGiRei2ICzzG",
					url: "http://giphy.com/gifs/funny-cat-FiGiRei2ICzzG",
	            }
	        ],
		next: "your-pagination-token"
	    }
  - Add a url paramter `next` for the pagination token to go to the next set of 5 results
  - You should be able to use the latest pagination token from each response to paginate through all of the results 

## Coding

1. Clone this repo and commit your code as you work

## Submitting

When you are satisfied with your work, follow these instructions to submit:

1. `git format-patch master --stdout > your-name.patch`.
    Or, if you worked straight off of master, use the commit sha preceding
    your work.
2. Email the patch to [recruiting+challenge-back-end@clara.com](mailto:recruiting+challenge-back-end@clara.com).

## Evaluation

We're going to be evaluating your project on the following criteria
  - How well did you meet all of the requirements?
  - How easy is it for someone new to understand your code?
  - How well are you using the tools and technologies in the language and framework of your choice?

## Feedback

We're always looking for ways to improve our processes at Clara so
let us know if anything is especially frustrating (or fun)!
