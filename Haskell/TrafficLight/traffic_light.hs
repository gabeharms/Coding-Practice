
main = do
  let res1 = Red == Red
      res2 = Green == Green
      res3 = Red == Yellow
      res4 = Red
   
  putStrLn $ show res1
  putStrLn $ show res2
  putStrLn $ show res3
  putStrLn $ show res4


data TrafficLight = Red | Yellow | Green

instance Eq TrafficLight where
    Red == Red = True
    Green == Green = True
    Yellow == Yellow = True
    _ == _ = False

instance Show TrafficLight where
    show Red = "Red light"
    show Yellow = "Yellow light"
    show Green = "Green light"
