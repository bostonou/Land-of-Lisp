(ns land-of-lisp.test.ch5
  (:use [land-of-lisp.ch5])
  (:use [clojure.test]))

(testing "Locations"
  (deftest describe-location-test
    (is (= (describe-location :living-room nodes) (nodes :living-room)))
    (is (= (describe-location :garden nodes) (nodes :garden)))
    (is (= (describe-location :attic nodes) (nodes :attic))))

  (deftest find-next-location-test
    (is (= (find-next-location 'west (edges :living-room)) :garden))
    (is (= (find-next-location 'upstairs (edges :living-room)) :attic))))

(testing "Paths"
  (deftest describe-path-test
    (is (= (describe-path '[garden west door])
          '[there is a door going west from here.]))
    (is (= (describe-path '[living-room downstairs ladder])
          '[there is a ladder going downstairs from here.])))

  (deftest describe-paths-test
    (is (= (describe-paths :room {:room '[[garden west door]
                                          [living-room downstairs ladder]]})
          '[there is a door going west from here. there is a ladder going downstairs from here.]))
    (is (= (describe-paths :room {:room '[[garden west door]]})
          '[there is a door going west from here.]))))

(testing "Objects"
  (deftest objects-at-test
    (is (= (objects-at :living-room (deref object-locations))
          '#{whiskey bucket}))
    (is (= (objects-at :garden (deref object-locations))
          '#{chain frog})))

  (deftest describe-objects-test
    (is (= (describe-objects :living-room (deref object-locations))
           '[you see a whiskey on the floor. you see a bucket on the floor.]))
    (is (= (describe-objects :garden (deref object-locations))
           '[you see a frog on the floor. you see a chain on the floor.]))))
