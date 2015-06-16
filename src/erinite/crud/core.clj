(ns erinite.crud.core)

;; Protocol for "pluggable" implementation

(defprotocol DataProvider
  "Abstract interface to data provider. Allows multiple backends (eg different databases) to be implemented."
  (-listing [this resource-type] "Get a list of all instances of resource with type `resource-type`")
  (-create! [this resource-type resource-data] "Create an instance of resource of type `resource-type` initialised with `resource-data`")
  (-read [this resource-type resource-id] "Get an instance of `resource-type` with id `resource-id`")
  (-update! [this resource-type resource-id resource-data] "Update instance of `resource-type` and id `resource-id` with data `resource-data`")
  (-delete! [this resource-type resource-id] "Delete instance of resource with type `resource-type` and id `resource-id1"))


;; Public API to data providers

(defn list-resources
  "List all resources of specified type in data provider."
  [provider type]
  {:pre [(satisfies? DataProvider provider)
         (keyword? type)]}
  (-listing provider type))

(defn create-resource!
  "Create a new instance of resource with given type."
  [provider type data]
  {:pre [(satisfies? DataProvider provider)
         (keyword? type)]}
  (-create! provider type data))

(defn read-resoruce
  "Read resource instance of given type and id from data provider."
  [provider type id]
  {:pre [(satisfies? DataProvider provider)
         (keyword? type)]}
  (-read provider type id))

(defn update-resource!
  "Update a resource of given type and id by merging data into existing resource
   instance."
  [provider type id data]
  {:pre [(satisfies? DataProvider provider)
         (keyword? type)]}
  (-update! provider type id data))

(defn delete-resource!
  "Delete instance of resource with given type and id."
  [provider type id]
  {:pre [(satisfies? DataProvider provider)
         (keyword? type)]}
  (-delete! provider type id))


;; Dummy data provider for testing

(defrecord MockDataProveder [resources]
  DataProvider
  (-listing [_ type]
    (keys (get @resources type)))

  (-create! [_ type data]
    (swap! resources assoc-in [type (str (java.util.UUID/randomUUID))] data))

  (-read [_ type id]
    (get-in @resources [type id]))

  (-update! [_ type id data]
    (swap! resources update-in [type id] merge data))

  (-delete! [_ type id]
    (swap! resources update-in [type] dissoc id)))


(defn make-mock-data-provider []
  (MockDataProveder. (atom {})))

