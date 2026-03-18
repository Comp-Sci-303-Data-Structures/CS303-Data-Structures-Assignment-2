CS303-Data-Structures-Assignment-2

Program Design



Tasks
Create a generic singly linked list class
  - head points to the first node; tail points to the last node.
  - size is kept in sync on every mutating operation.
  - If the list is empty, removeFirst / removeLast / getFirst / getLast
 *   throw NoSuchElementException if list is empty

Create Stack operations using ArrayLists
 * - The END of the ArrayList is treated as the TOP of the stack (LIFO).
 *   push  → list.add(value)          
 *   pop   → list.remove(size - 1)
 *   peek  → list.get(size - 1)
 * - pop() and peek() throw EmptyStackException when called on an empty stack.

Terminal
=== SingleLinkedList<T> Demo ===

After addFirst(5), addLast(10,20,30): [5, 10, 20, 30]
getFirst(): 5
getLast():  30
After insert(2, 15):  [5, 10, 15, 20, 30]
After insert(0, 1):   [1, 5, 10, 15, 20, 30]
After insert(100, 99): [1, 5, 10, 15, 20, 30, 99]
find(15): index = 3
find(99): index = 6
find(42): index = 7 (not found → size)
remove(0) returned true: [5, 10, 15, 20, 30, 99]
remove(last) returned true: [5, 10, 15, 20, 30]
remove(100) returned false (out of bounds)
removeFirst(): 5 → [10, 15, 20, 30]
removeLast():  30 → [10, 15, 20]
isEmpty(): false

Final size: 3

Terminal
=== IntStack Demo ===

isEmpty() before any pushes: true
push(4)  → Stack (bottom → top): [4]
push(8)  → Stack (bottom → top): [4, 8]
push(15)  → Stack (bottom → top): [4, 8, 15]
push(16)  → Stack (bottom → top): [4, 8, 15, 16]
push(23)  → Stack (bottom → top): [4, 8, 15, 16, 23]
push(42)  → Stack (bottom → top): [4, 8, 15, 16, 23, 42]

Stack size after pushes: 6

pop()  → removed value: 42
Stack after pop: Stack (bottom → top): [4, 8, 15, 16, 23]

peek() → current top: 23
Stack unchanged:  Stack (bottom → top): [4, 8, 15, 16, 23]

average() → 13.20

--- Edge-case demo: pop() on empty stack ---
Caught EmptyStackException from pop() ✓
Caught EmptyStackException from peek() ✓

Final stack size: 5
isEmpty(): false