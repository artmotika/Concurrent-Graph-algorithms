# Concurrent Graph algorithms

Процессор Intel core I5 10th, 1.1ghz, 6Mb - кэш, 4 ядра, 8 потоков 

| Graphs                  | Dijkstra sequential (1 vertex)| Dijkstra parallel (1 vertex)| Floyd sequential| Floyd parallel |
| :---------------------- | :---------------------------: | :-------------------------: | :-------------: | :------------: | 
| №1 Random 10 vertices   | 0.017                         | 0.016                       | 0.003           | 0.014          |
| №2 Random 100 vertices  | 0.04                          | 0.045                       | 0.080           | 0.065          |
| №3 Random 1000 vertices | 0.808                         | 0.366                       | 52.52           | 44.23          |
| №4 Random 1500 vertices | 1.74                          | 0.54                        | 124.69          | 84.87          |
| №5 Random 10000 vertices| 34.75                         | 9.71                        | ?               | ?              |
