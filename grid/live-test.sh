#!/bin/sh

# Run tests every time a change occurs.
# Take a look at https://medium.com/@chapuyj/automatically-run-tests-when-a-change-occurs-9bae2140586b

fswatch -or ./src ./test | xargs -n1 -I{} lein test