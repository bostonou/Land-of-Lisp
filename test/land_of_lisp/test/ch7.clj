(ns land-of-lisp.test.ch7
  (:use [land-of-lisp.ch7])
  (:use [clojure.test]))

(testing "Dot format"
  (deftest dot-name-test
    (is (= (dot-name :living-room) "LIVING_ROOM"))
    (is (= (dot-name :foo!) "FOO_"))
    (is (= (dot-name :24) "24")))

  (deftest dot-label-test
    (is (= (dot-label '[you are here])
           "[you are here]"))
    (is (= (dot-label '[Long text max of 30 chars goes here then dots])
           "[Long text max of 30 chars goe..."))))

(testing "Node Data"
  (deftest nodes->dot-test
    (let [nodes {:living-room '[you are in the living room.]
                 :garden '[you are in a beautiful garden.]
                 :attic '[you are in the attic.]}
          expected #{"LIVING_ROOM[label=\"LIVING_ROOM: [you are in the living room.]\"];"
                     "GARDEN[label=\"GARDEN: [you are in a beautiful garden...\"];"
                     "ATTIC[label=\"ATTIC: [you are in the attic.]\"];"}]
      (is (= #{} (clojure.set/difference (nodes->dot nodes) expected))))))

(testing "Edge Data"
  (deftest edges->dot-test
    (let [edges {:living-room '[[:garden west door] [:attic upstairs ladder]]
                 :garden '[[:living-room east door]]
                 :attic '[[:living-room downstairs ladder]]}
          expected #{"LIVING_ROOM->GARDEN[label=\"[west door]\"];"
                     "LIVING_ROOM->ATTIC[label=\"[upstairs ladder]\"];"
                     "GARDEN->LIVING_ROOM[label=\"[east door]\"];"
                     "ATTIC->LIVING_ROOM[label=\"[downstairs ladder]\"];"}]
      (is (= #{} (clojure.set/difference (edges->dot edges) expected))))))
