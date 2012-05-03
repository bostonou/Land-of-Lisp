(ns land-of-lisp.ch5
  [:require land-of-lisp.core])

(def user-location (ref :living-room))

(def nodes {:living-room '[you are in the living room. a wizard is snoring loudly on the couch.]
            :garden '[you are in a beautiful garden. there is a well in front of you.]
            :attic '[you are in the attic. there is a giant welding torch in the corner.]})

(def edges {:living-room '[[:garden west door] [:attic upstairs ladder]]
            :garden '[[:living-room east door]]
            :attic '[[:living-room downstairs ladder]]})

(def object-locations {:living-room '[whiskey bucket]
                        :garden '[chain frog]})

(defn find-next-location
  [direction edges]
  (let [[edge & rest] edges]
    (cond
      (nil? edge) nil
      (= (second edge) direction) (first edge)
      true (find-next-location direction rest))))

(defn describe-location
  [location descriptions]
  (descriptions location))

(defn describe-path
  [edge]
  (let [[location direction path] edge]
    (vector 'there 'is 'a path 'going direction 'from 'here.)))

(defn describe-paths
  [location edges]
  (apply #'concat (map #'describe-path (edges location))))

(defn objects-at
  [location obj-locs]
  (obj-locs location))

(defn describe-object
  [object]
  (vector 'you 'see 'a object 'on 'the 'floor.))

(defn describe-objects
  [location obj-locs]
  (apply #'concat (map #'describe-object (obj-locs location))))

;non-functional UI
(defn look
  []
  (concat (describe-location (deref user-location) nodes)
          (describe-paths (deref user-location) edges)
          (describe-objects (deref user-location) object-locations)))

(defn walk
  [direction]
  (let [avail-edges (edges (deref user-location))
        location (find-next-location direction avail-edges)]
    (cond
      (nil? location) '[you cannot go that way.]
      true (do
             (dosync (ref-set user-location location))
             (look)))))
