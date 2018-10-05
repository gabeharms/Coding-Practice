data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show, Read, Eq)

main = do
  let nums = [8,6,4,1,7,3,5]
      numsTree = foldr treeInsert EmptyTree nums
      res1 = 8 `treeElem` numsTree
      res2 = 10 `treeElem` numsTree
      res3 = 3 `treeElem` numsTree

  putStrLn $ show res1
  putStrLn $ show res2
  putStrLn $ show res3


      
 

singleton :: a -> Tree a
singleton x = Node x EmptyTree EmptyTree

treeInsert :: (Ord a) => a -> Tree a -> Tree a
treeInsert x EmptyTree = singleton x
treeInsert x (Node a left right)
    | x == a  = Node x left right
    | x < a   = Node a (treeInsert x left) right
    | x > a   = Node a left (treeInsert x right)

treeElem :: (Ord a) => a -> Tree a -> Bool
treeElem x EmptyTree = False
treeElem x (Node a left right)
    | x == a = True
    | x < a  = treeElem x left
    | x > a  = treeElem x right

