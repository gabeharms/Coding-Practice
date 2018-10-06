
main = do
  putStrLn $ show $ solveRPN "10 4 3 + 2 * -"
  putStrLn $ show $ solveRPN "90 34 12 33 55 66 + * - +" 
  putStrLn $ show $ solveRPN "90 34 12 33 55 66 + * - + -"


solveRPN :: (Num a, Read a) => String -> a  
solveRPN = head . foldl foldingFunction [] . words  
    where   foldingFunction (x:y:ys) "*" = (x * y):ys  
            foldingFunction (x:y:ys) "+" = (x + y):ys  
            foldingFunction (x:y:ys) "-" = (y - x):ys  
            foldingFunction xs numberString = read numberString:xs  
