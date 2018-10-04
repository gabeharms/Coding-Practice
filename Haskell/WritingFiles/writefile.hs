import System.IO
import Data.Char

main = do
  contents <- readFile "input.txt"
  writeFile "output.txt" $ map toUpper contents
