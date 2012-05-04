(ns land-of-lisp.test.ch5
  (:use [land-of-lisp.ch5])
  (:use [clojure.test]))

;defaults
(dosync
  (ref-set object-locations
           {:living-room '#{whiskey bucket} :garden '#{chain frog}}))

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

(testing "Tracking Objects"
  (deftest move-object-test
    (let [obj-locs {:frig '#{beer wine}}]
      (is (= (move-object :frig :body 'beer obj-locs)
             {:frig '#{wine} :body '#{beer}}))
      (is (nil? (move-object :frig :body 'wine-cooler obj-locs)))
      (is (nil? (move-object :sidewalk :body 'beer obj-locs)))))
         
  (deftest remove-object-test
    (let [objs '[wine beer ice]]
      (is (= (remove-object 'beer objs) '[wine ice]))
      (is (= (remove-object 'water objs) '[wine beer ice]))))
         
  (deftest add-object-test
    (let [objs '[wine ice]]
      (is (= (add-object 'wine objs) '[wine ice]))
      (is (= (add-object 'beer objs) '[wine ice beer])))))

  ;(deftest object-location?-test
   ; (let [obj-locs 
