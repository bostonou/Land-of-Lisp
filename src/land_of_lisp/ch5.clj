(ns land-of-lisp.ch5
  [:require land-of-lisp.core])

(def nodes {:living-room '(you are in the living room. a wizard is snoring loudly on the couch.)
            :garden '(you are in a beautiful garden. there is a well in front of you.)
            :attic '(you are in the attic. there is a giant welding torch in the corner.)})

(def edges {:living-room ['(garden west door) '(attic upstairs ladder)]
            :garden '(living-room east door)
            :attic '(living-room downstairs ladder)})

(defn describe-location
  [location descriptions]
  (descriptions location))

(defn describe-path
  [[location direction path]]
  (list 'there 'is 'a path 'going direction 'from 'here.))
