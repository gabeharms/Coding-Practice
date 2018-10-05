import qualified Data.Map as Map

main = do
  let lockers = Map.fromList [(100,(Taken,"ZD39I")) ,(101,(Free,"JAH3I"))  ,(103,(Free,"IQSA9"))  ,(105,(Free,"QOTSA"))  ,(109,(Taken,"893JJ"))  ,(110,(Taken,"99292"))  ]
      res1 = case lockerLookup 0 lockers of
        Right code -> show code
        Left str -> str
      res2 = case lockerLookup 100 lockers of
        Right code -> show code
        Left str -> str
      res3 = case lockerLookup 101 lockers of
        Right code -> show code
        Left str -> str

  putStrLn $ show res1
  putStrLn $ show res2
  putStrLn $ show res3


data LockerState = Taken | Free deriving (Show, Eq)
type Code = String
type LockerMap = Map.Map Int (LockerState, Code)

lockerLookup :: Int -> LockerMap -> Either String Code
lockerLookup lockerNumber lockers = 
  case Map.lookup lockerNumber lockers of
    Nothing -> Left $ "Locker number " ++ show lockerNumber ++ " doesn't exist!"
    Just (state, code) -> if state /= Taken
                            then Right code
                            else Left $ "Locker " ++ show lockerNumber ++ " is already taken!"
