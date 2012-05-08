(ns land-of-lisp.test.ch7
  (:use [land-of-lisp.ch7])
  (:use [clojure.test]))

(testing "Dot format"
  (deftest dot-name-test
    (is (= (dot-name :living-room) "LIVING_ROOM"))
    (is (= (dot-name :foo!) "FOO_"))
    (is (= (dot-name :24) "24")))

  (deftest dot-label-test
    (is (= (dot-label :garden '[you are here])
           "GARDEN: [you are here]"))
    (is (= (dot-label :lost '[Long text max of 30 chars goes here then dots])
           "LOST: [Long text max of 30 cha..."))))

(testing "Node Data"
  (deftest nodes->dot-test
    (let [nodes {:living-room '[you are in the living room.]
                 :garden '[you are in a beautiful garden.]
                 :attic '[you are in the attic.]}
          expected #{"LIVING_ROOM[label=\"LIVING_ROOM: [you are in the l...\"];"
                     "GARDEN[label=\"GARDEN: [you are in a beautifu...\"];"
                     "ATTIC[label=\"ATTIC: [you are in the attic.]\"];"}]
      (is (= #{} (clojure.set/difference (nodes->dot nodes) expected))))))

(testing "Edge Data"
  (deftest edges->dot-test
    (let [edges {:living-room '[[:garden west door] [:attic upstairs ladder]]
                 :garden '[[:living-room east door]]
                 :attic '[[:living-room downstairs ladder]]}
          expected #{"LIVING_ROOM[label=\"GARDEN: [west door]\"];"
                     "LIVING_ROOM[label=\"ATTIC: [upstairs ladder]\"];"
                     "GARDEN[label=\"LIVING_ROOM: [east door]\"];"
                     "ATTIC[label=\"LIVING_ROOM: [downstairs ladde...\"];"}]
      (is (= #{} (clojure.set/difference (edges->dot edges) expected))))))
