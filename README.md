# erinite/crud
Library to provide basic CRUD functionality to server-side Clojure

CRUD functionality is a common part of many web projects. It often isn't the main focus, so it shouldn't be something to spend much effort on. Erinite/crud aims to make it quick and easy to add CRUD functionality into a larger project, so that resources can be spent on whats important and not on reimplementing basic operations.

Erinite/crud takes a data-driven approach: you tell it what resources are available and it gives you a basic API to create, read, update and delete instances of these resources. The API is designed to be usable directly in your Clojure code, or as handlers to hook into any Ring web app so that you can expose it as a HTTP API.

## Installation

Add the following to your `:dependencies`:

[![Clojars Project](http://clojars.org/crud/template/latest-version.svg)](http://clojars.org/erinite/crud)

If you do not use leiningen, click the above banner to get instructions for
maven.


## Usage

### Example

```clj
(require '[erinite.crud.core :as crud])
```
