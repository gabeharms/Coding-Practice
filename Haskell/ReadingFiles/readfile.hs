import System.IO

main = do
  handle <- openFile "input.txt" ReadMode
  contents <- hGetContents handle
  putStr contents
  hClose handle
