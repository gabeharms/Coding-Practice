main = do
  let vec1 = (Vector 1 2 3)
      vec2 = (Vector 4 5 6)
      result1 = vec1 `scalarMult` vec2
      result2 = vec1 `vectMult` 10
  putStrLn $ show result1
  putStrLn $ toStr result2

data Vector a = Vector a a a deriving (Show)

vplus :: (Num t) => Vector t -> Vector t -> Vector t
(Vector i j k) `vplus` (Vector l m n) = Vector (i+l) (j+m) (k+n)

vectMult :: (Num t) => Vector t -> t -> Vector t
(Vector i j k) `vectMult` m = Vector (i*m) (j*m) (k*m)

scalarMult :: (Num t) => Vector t -> Vector t -> t
(Vector i j k) `scalarMult` (Vector l m n) = i*l + j*m + k*n

toStr :: (Num t, Show t) => Vector t -> [Char]
toStr vec = show $ vec `scalarMult` (Vector 1 1 1)
