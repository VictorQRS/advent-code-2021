import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Board {
    List<List<Integer>> _board;
    
    public Board(List<List<Integer>> board) {
        _board = board;
    }
    
    public void mark(Integer draw) {
        for(List<Integer> row : _board) {
            for (int i=0; i<row.size(); i++) {
                if (row.get(i) == draw) {
                    row.set(i, -1);
                }
            }
        }
    }
    
    public boolean hasWon() {
        // check rows
        for (List<Integer> row : _board) {
            int count = 0;
            for (Integer number : row) {
                if (number != -1) {
                    count++;
                }
            }
            
            if (count == 0) {
                return true;
            }
        }
        
        // check cols
        int colLength = _board.get(0).size();
        for (int i=0; i<colLength; i++) {
            int count = 0;
            for (int j=0; j<_board.size();j++){
                if (_board.get(j).get(i) != -1) {
                    count++;
                }
            }
            
            if (count == 0) {
                return true;
            }
        }
        
        return false;
    }
    
    public List<List<Integer>> getNumbers() {
        return _board;
    }
}

class Game {
    List<Integer> _draws;
    Integer _lastDrawn;
    
    List<Board> _boards = new ArrayList<>();
    Board _lastWinner;
    
    public void addDraws(List<Integer> draws) {
        _draws = draws;
    }
    
    public void add(Board board) {
        _boards.add(board);
    }
    
    public Board getLastWinner() {
        return _lastWinner;
    }
    
    public Integer getLastDrawn() {
        return _lastDrawn;
    }
    
    public void run() {
        do {
            _lastDrawn = drawAnother();
            markBoards(_lastDrawn);
            removeWinnerBoards();
        } while(_boards.size() > 1);
        
        // keep playing to get winner state on board
        _lastWinner = _boards.get(0);
        do {
            _lastDrawn = drawAnother();
            _lastWinner.mark(_lastDrawn);
        } while(!_lastWinner.hasWon());
    }
    
    Integer drawAnother() {
        return _draws.remove(0);
    }
    
    void markBoards(Integer draw) {
        for(Board board : _boards) {
            board.mark(draw);
        }
    }
    
    void removeWinnerBoards() {
        _boards.removeIf(board -> board.hasWon());
    }
}

public class Main {
	public static void main(String[] args) {
	    Game game = new Game();
	    
	    game.addDraws(new ArrayList<>(Arrays.asList(85,84,30,15,46,71,64,45,13,
	        90,63,89,62,25,87,68,73,47,65,78,2,27,67,95,88,99,96,17,42,31,91,98,
	        57,28,38,93,43,0,55,49,22,24,82,54,59,52,3,26,9,32,4,48,39,50,80,21,
	        5,1,23,10,58,34,12,35,74,8,6,79,40,76,86,69,81,61,14,92,97,19,7,51,
	        33,11,77,75,20,70,29,36,60,18,56,37,72,41,94,44,83,66,16,53)));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(78, 13, 8, 62, 67)),
			new ArrayList<>(Arrays.asList(42, 89, 97, 16, 65)),
			new ArrayList<>(Arrays.asList( 5, 12, 73, 50, 56)),
			new ArrayList<>(Arrays.asList(45, 10, 63, 41, 64)),
			new ArrayList<>(Arrays.asList(49, 1, 95, 71, 17))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(60, 25, 66, 82, 22)),
			new ArrayList<>(Arrays.asList(94, 45, 68, 5, 12)),
			new ArrayList<>(Arrays.asList(46, 44, 48, 31, 34)),
			new ArrayList<>(Arrays.asList(10, 56, 37, 96, 81)),
			new ArrayList<>(Arrays.asList(99, 39, 84, 32, 6))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(11, 86, 77, 36, 2)),
			new ArrayList<>(Arrays.asList(57, 68, 27, 74, 4)),
			new ArrayList<>(Arrays.asList(81, 92, 49, 37, 51)),
			new ArrayList<>(Arrays.asList(78, 43, 94, 46, 63)),
			new ArrayList<>(Arrays.asList(13, 52, 72, 17, 44))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(88, 13, 81, 21, 20)),
			new ArrayList<>(Arrays.asList(80, 99, 23, 37, 53)),
			new ArrayList<>(Arrays.asList(44, 68, 15, 38, 55)),
			new ArrayList<>(Arrays.asList(84, 48, 82, 97, 6)),
			new ArrayList<>(Arrays.asList( 4, 43, 52, 72, 31))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(39, 62, 45, 86, 44)),
			new ArrayList<>(Arrays.asList(12, 17, 16, 7, 6)),
			new ArrayList<>(Arrays.asList(84, 42, 82, 34, 85)),
			new ArrayList<>(Arrays.asList(19, 77, 9, 48, 98)),
			new ArrayList<>(Arrays.asList(21, 99, 67, 26, 69))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 1, 75, 50, 5, 44)),
			new ArrayList<>(Arrays.asList( 3, 28, 62, 17, 43)),
			new ArrayList<>(Arrays.asList(14, 52, 64, 77, 81)),
			new ArrayList<>(Arrays.asList(32, 89, 7, 11, 70)),
			new ArrayList<>(Arrays.asList(38, 36, 71, 45, 58))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(53, 32, 35, 69, 63)),
			new ArrayList<>(Arrays.asList( 6, 21, 75, 64, 96)),
			new ArrayList<>(Arrays.asList(10, 89, 15, 48, 26)),
			new ArrayList<>(Arrays.asList(23, 20, 43, 57, 33)),
			new ArrayList<>(Arrays.asList(18, 49, 51, 47, 74))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(20, 79, 9, 74, 13)),
			new ArrayList<>(Arrays.asList(52, 28, 77, 26, 43)),
			new ArrayList<>(Arrays.asList(57, 83, 4, 25, 70)),
			new ArrayList<>(Arrays.asList(90, 1, 30, 53, 38)),
			new ArrayList<>(Arrays.asList(56, 66, 82, 35, 51))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(12, 3, 31, 93, 8)),
			new ArrayList<>(Arrays.asList(20, 27, 51, 78, 9)),
			new ArrayList<>(Arrays.asList(29, 46, 82, 85, 75)),
			new ArrayList<>(Arrays.asList(15, 76, 91, 70, 63)),
			new ArrayList<>(Arrays.asList(59, 39, 13, 43, 79))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(46, 35, 15, 13, 2)),
			new ArrayList<>(Arrays.asList(65, 69, 97, 77, 87)),
			new ArrayList<>(Arrays.asList(64, 59, 94, 88, 40)),
			new ArrayList<>(Arrays.asList(34, 79, 92, 93, 58)),
			new ArrayList<>(Arrays.asList(47, 28, 74, 82, 29))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(32, 38, 24, 68, 12)),
			new ArrayList<>(Arrays.asList( 8, 78, 79, 89, 43)),
			new ArrayList<>(Arrays.asList(67, 54, 6, 98, 48)),
			new ArrayList<>(Arrays.asList( 1, 14, 83, 15, 37)),
			new ArrayList<>(Arrays.asList(44, 10, 97, 74, 33))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 9, 95, 2, 99, 1)),
			new ArrayList<>(Arrays.asList( 8, 42, 60, 56, 40)),
			new ArrayList<>(Arrays.asList(32, 11, 71, 14, 80)),
			new ArrayList<>(Arrays.asList(77, 6, 68, 46, 48)),
			new ArrayList<>(Arrays.asList(98, 70, 39, 44, 62))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(43, 94, 41, 13, 15)),
			new ArrayList<>(Arrays.asList(96, 99, 35, 27, 8)),
			new ArrayList<>(Arrays.asList(22, 75, 73, 17, 90)),
			new ArrayList<>(Arrays.asList(62, 23, 5, 88, 3)),
			new ArrayList<>(Arrays.asList(10, 52, 61, 60, 57))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(31, 62, 74, 3, 79)),
			new ArrayList<>(Arrays.asList(15, 49, 60, 28, 71)),
			new ArrayList<>(Arrays.asList(66, 2, 11, 36, 41)),
			new ArrayList<>(Arrays.asList(34, 80, 33, 94, 75)),
			new ArrayList<>(Arrays.asList(64, 56, 84, 70, 16))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(98, 94, 68, 32, 26)),
			new ArrayList<>(Arrays.asList(61, 7, 52, 66, 18)),
			new ArrayList<>(Arrays.asList(40, 20, 82, 81, 74)),
			new ArrayList<>(Arrays.asList(28, 36, 89, 14, 35)),
			new ArrayList<>(Arrays.asList(71, 11, 44, 13, 72))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(81, 30, 6, 86, 37)),
			new ArrayList<>(Arrays.asList(46, 45, 64, 83, 62)),
			new ArrayList<>(Arrays.asList( 7, 70, 38, 51, 15)),
			new ArrayList<>(Arrays.asList(91, 41, 26, 40, 4)),
			new ArrayList<>(Arrays.asList(87, 0, 82, 74, 60))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(83, 99, 26, 69, 1)),
			new ArrayList<>(Arrays.asList( 6, 98, 53, 31, 43)),
			new ArrayList<>(Arrays.asList(82, 64, 42, 90, 34)),
			new ArrayList<>(Arrays.asList(87, 62, 11, 40, 39)),
			new ArrayList<>(Arrays.asList(77, 51, 2, 30, 97))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(96, 5, 24, 44, 32)),
			new ArrayList<>(Arrays.asList(48, 92, 78, 74, 76)),
			new ArrayList<>(Arrays.asList(99, 33, 93, 97, 49)),
			new ArrayList<>(Arrays.asList(45, 8, 88, 66, 59)),
			new ArrayList<>(Arrays.asList(52, 64, 29, 60, 82))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(69, 23, 59, 96, 71)),
			new ArrayList<>(Arrays.asList(14, 93, 21, 44, 62)),
			new ArrayList<>(Arrays.asList(65, 84, 2, 39, 1)),
			new ArrayList<>(Arrays.asList( 0, 68, 38, 81, 4)),
			new ArrayList<>(Arrays.asList(48, 31, 26, 60, 34))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(24, 46, 44, 52, 98)),
			new ArrayList<>(Arrays.asList(65, 23, 31, 89, 5)),
			new ArrayList<>(Arrays.asList(34, 79, 75, 96, 41)),
			new ArrayList<>(Arrays.asList(76, 28, 90, 12, 11)),
			new ArrayList<>(Arrays.asList(68, 29, 38, 70, 50))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(51, 0, 45, 23, 20)),
			new ArrayList<>(Arrays.asList(44, 49, 12, 31, 7)),
			new ArrayList<>(Arrays.asList(41, 26, 46, 75, 92)),
			new ArrayList<>(Arrays.asList(90, 30, 72, 95, 55)),
			new ArrayList<>(Arrays.asList(87, 57, 10, 99, 40))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(25, 67, 80, 74, 44)),
			new ArrayList<>(Arrays.asList( 3, 82, 27, 81, 11)),
			new ArrayList<>(Arrays.asList(33, 42, 97, 57, 70)),
			new ArrayList<>(Arrays.asList(19, 94, 0, 2, 49)),
			new ArrayList<>(Arrays.asList( 6, 90, 60, 29, 58))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(79, 59, 96, 68, 14)),
			new ArrayList<>(Arrays.asList(38, 70, 65, 66, 69)),
			new ArrayList<>(Arrays.asList(36, 75, 20, 18, 29)),
			new ArrayList<>(Arrays.asList(64, 88, 35, 61, 43)),
			new ArrayList<>(Arrays.asList(57, 76, 62, 23, 25))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(60, 9, 81, 94, 62)),
			new ArrayList<>(Arrays.asList(73, 20, 87, 72, 14)),
			new ArrayList<>(Arrays.asList(95, 63, 42, 51, 13)),
			new ArrayList<>(Arrays.asList(75, 83, 32, 30, 66)),
			new ArrayList<>(Arrays.asList(97, 6, 80, 82, 17))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 3, 88, 31, 43, 68)),
			new ArrayList<>(Arrays.asList(20, 78, 47, 10, 91)),
			new ArrayList<>(Arrays.asList(14, 42, 40, 74, 39)),
			new ArrayList<>(Arrays.asList( 5, 32, 16, 97, 1)),
			new ArrayList<>(Arrays.asList( 9, 33, 49, 70, 36))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(77, 31, 65, 27, 52)),
			new ArrayList<>(Arrays.asList(49, 74, 57, 25, 66)),
			new ArrayList<>(Arrays.asList(24, 4, 39, 33, 1)),
			new ArrayList<>(Arrays.asList(23, 14, 19, 2, 21)),
			new ArrayList<>(Arrays.asList(80, 71, 29, 81, 91))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(32, 68, 47, 3, 88)),
			new ArrayList<>(Arrays.asList( 1, 97, 99, 28, 80)),
			new ArrayList<>(Arrays.asList( 2, 25, 18, 31, 51)),
			new ArrayList<>(Arrays.asList(26, 10, 73, 34, 40)),
			new ArrayList<>(Arrays.asList( 8, 55, 45, 36, 37))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(79, 81, 33, 94, 51)),
			new ArrayList<>(Arrays.asList(84, 4, 91, 0, 69)),
			new ArrayList<>(Arrays.asList(49, 80, 35, 67, 20)),
			new ArrayList<>(Arrays.asList(98, 48, 64, 38, 30)),
			new ArrayList<>(Arrays.asList(25, 83, 45, 97, 42))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(18, 5, 84, 94, 50)),
			new ArrayList<>(Arrays.asList(36, 47, 2, 52, 65)),
			new ArrayList<>(Arrays.asList(39, 77, 83, 37, 80)),
			new ArrayList<>(Arrays.asList(51, 88, 15, 12, 31)),
			new ArrayList<>(Arrays.asList(87, 17, 68, 48, 67))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(39, 95, 30, 8, 86)),
			new ArrayList<>(Arrays.asList(45, 57, 40, 51, 60)),
			new ArrayList<>(Arrays.asList(85, 88, 33, 93, 25)),
			new ArrayList<>(Arrays.asList(76, 52, 37, 68, 6)),
			new ArrayList<>(Arrays.asList(11, 80, 69, 19, 71))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 6, 71, 25, 66, 54)),
			new ArrayList<>(Arrays.asList(33, 17, 98, 63, 20)),
			new ArrayList<>(Arrays.asList(27, 14, 44, 43, 18)),
			new ArrayList<>(Arrays.asList(68, 10, 50, 35, 65)),
			new ArrayList<>(Arrays.asList(61, 3, 83, 12, 13))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(46, 21, 43, 15, 19)),
			new ArrayList<>(Arrays.asList(99, 82, 8, 95, 80)),
			new ArrayList<>(Arrays.asList( 1, 10, 45, 58, 53)),
			new ArrayList<>(Arrays.asList(23, 94, 50, 66, 52)),
			new ArrayList<>(Arrays.asList(57, 98, 26, 77, 90))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(11, 50, 55, 28, 79)),
			new ArrayList<>(Arrays.asList( 4, 3, 26, 57, 56)),
			new ArrayList<>(Arrays.asList(68, 86, 10, 87, 69)),
			new ArrayList<>(Arrays.asList(32, 35, 89, 63, 29)),
			new ArrayList<>(Arrays.asList(66, 27, 33, 8, 30))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(23, 34, 94, 93, 47)),
			new ArrayList<>(Arrays.asList( 7, 71, 9, 52, 50)),
			new ArrayList<>(Arrays.asList(45, 79, 13, 43, 86)),
			new ArrayList<>(Arrays.asList( 0, 51, 17, 6, 26)),
			new ArrayList<>(Arrays.asList( 4, 82, 44, 38, 37))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(49, 24, 16, 64, 32)),
			new ArrayList<>(Arrays.asList(46, 84, 3, 29, 51)),
			new ArrayList<>(Arrays.asList(71, 82, 33, 61, 26)),
			new ArrayList<>(Arrays.asList(15, 5, 94, 86, 41)),
			new ArrayList<>(Arrays.asList(63, 36, 10, 67, 43))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(94, 17, 3, 71, 91)),
			new ArrayList<>(Arrays.asList(93, 50, 88, 36, 27)),
			new ArrayList<>(Arrays.asList(54, 68, 7, 8, 34)),
			new ArrayList<>(Arrays.asList( 9, 92, 37, 45, 52)),
			new ArrayList<>(Arrays.asList(47, 29, 70, 10, 69))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(79, 27, 30, 0, 12)),
			new ArrayList<>(Arrays.asList(51, 70, 19, 89, 20)),
			new ArrayList<>(Arrays.asList( 2, 42, 64, 21, 49)),
			new ArrayList<>(Arrays.asList(48, 39, 1, 3, 56)),
			new ArrayList<>(Arrays.asList(98, 35, 95, 82, 72))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(91, 71, 65, 95, 44)),
			new ArrayList<>(Arrays.asList(26, 72, 92, 59, 43)),
			new ArrayList<>(Arrays.asList(61, 93, 6, 4, 90)),
			new ArrayList<>(Arrays.asList(76, 31, 8, 1, 29)),
			new ArrayList<>(Arrays.asList(82, 64, 89, 22, 45))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(55, 4, 1, 42, 87)),
			new ArrayList<>(Arrays.asList(88, 34, 67, 83, 45)),
			new ArrayList<>(Arrays.asList(22, 23, 98, 24, 12)),
			new ArrayList<>(Arrays.asList(74, 72, 49, 32, 25)),
			new ArrayList<>(Arrays.asList(73, 7, 19, 26, 3))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 0, 43, 50, 57, 80)),
			new ArrayList<>(Arrays.asList(68, 21, 87, 1, 91)),
			new ArrayList<>(Arrays.asList(60, 6, 81, 78, 99)),
			new ArrayList<>(Arrays.asList(35, 98, 72, 49, 16)),
			new ArrayList<>(Arrays.asList(36, 25, 13, 48, 22))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(59, 1, 26, 3, 71)),
			new ArrayList<>(Arrays.asList(43, 55, 50, 7, 16)),
			new ArrayList<>(Arrays.asList( 5, 64, 29, 38, 84)),
			new ArrayList<>(Arrays.asList(41, 23, 60, 19, 24)),
			new ArrayList<>(Arrays.asList(85, 58, 49, 98, 33))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(80, 48, 3, 65, 38)),
			new ArrayList<>(Arrays.asList(30, 97, 96, 45, 7)),
			new ArrayList<>(Arrays.asList( 6, 85, 8, 90, 40)),
			new ArrayList<>(Arrays.asList(37, 78, 84, 16, 24)),
			new ArrayList<>(Arrays.asList(69, 11, 43, 64, 63))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(28, 14, 19, 1, 97)),
			new ArrayList<>(Arrays.asList(37, 39, 86, 23, 64)),
			new ArrayList<>(Arrays.asList(20, 67, 85, 65, 90)),
			new ArrayList<>(Arrays.asList(54, 51, 59, 91, 43)),
			new ArrayList<>(Arrays.asList(17, 30, 11, 24, 7))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(22, 88, 27, 43, 10)),
			new ArrayList<>(Arrays.asList(35, 3, 72, 52, 57)),
			new ArrayList<>(Arrays.asList(61, 54, 28, 69, 37)),
			new ArrayList<>(Arrays.asList(71, 78, 96, 82, 81)),
			new ArrayList<>(Arrays.asList(33, 39, 32, 40, 7))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(50, 60, 69, 33, 57)),
			new ArrayList<>(Arrays.asList(84, 22, 95, 74, 6)),
			new ArrayList<>(Arrays.asList(90, 94, 71, 45, 68)),
			new ArrayList<>(Arrays.asList(72, 86, 77, 9, 24)),
			new ArrayList<>(Arrays.asList(73, 12, 89, 13, 1))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(66, 35, 36, 87, 73)),
			new ArrayList<>(Arrays.asList(77, 96, 52, 47, 68)),
			new ArrayList<>(Arrays.asList(63, 4, 83, 20, 95)),
			new ArrayList<>(Arrays.asList(17, 70, 9, 18, 50)),
			new ArrayList<>(Arrays.asList(98, 40, 25, 60, 26))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(31, 37, 81, 34, 56)),
			new ArrayList<>(Arrays.asList( 3, 15, 43, 51, 35)),
			new ArrayList<>(Arrays.asList(67, 70, 1, 20, 12)),
			new ArrayList<>(Arrays.asList(80, 54, 69, 17, 88)),
			new ArrayList<>(Arrays.asList(65, 91, 60, 8, 53))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(76, 23, 87, 41, 18)),
			new ArrayList<>(Arrays.asList(49, 58, 92, 98, 25)),
			new ArrayList<>(Arrays.asList(77, 53, 44, 17, 27)),
			new ArrayList<>(Arrays.asList(67, 28, 37, 66, 95)),
			new ArrayList<>(Arrays.asList(59, 39, 33, 4, 34))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 0, 25, 2, 5, 22)),
			new ArrayList<>(Arrays.asList(26, 85, 90, 51, 21)),
			new ArrayList<>(Arrays.asList(31, 79, 10, 41, 45)),
			new ArrayList<>(Arrays.asList(69, 56, 1, 67, 40)),
			new ArrayList<>(Arrays.asList(59, 98, 99, 89, 6))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(95, 67, 72, 52, 78)),
			new ArrayList<>(Arrays.asList(88, 61, 96, 11, 43)),
			new ArrayList<>(Arrays.asList(34, 73, 53, 54, 8)),
			new ArrayList<>(Arrays.asList(71, 3, 70, 42, 58)),
			new ArrayList<>(Arrays.asList(12, 82, 97, 68, 98))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(20, 10, 13, 74, 89)),
			new ArrayList<>(Arrays.asList(82, 25, 45, 92, 61)),
			new ArrayList<>(Arrays.asList(58, 62, 0, 22, 57)),
			new ArrayList<>(Arrays.asList(68, 90, 36, 18, 75)),
			new ArrayList<>(Arrays.asList(48, 39, 69, 4, 52))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(40, 3, 86, 33, 98)),
			new ArrayList<>(Arrays.asList(30, 67, 39, 7, 69)),
			new ArrayList<>(Arrays.asList(80, 64, 77, 54, 51)),
			new ArrayList<>(Arrays.asList(24, 49, 6, 68, 61)),
			new ArrayList<>(Arrays.asList(62, 94, 1, 26, 50))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(89, 88, 7, 21, 87)),
			new ArrayList<>(Arrays.asList(83, 10, 78, 27, 97)),
			new ArrayList<>(Arrays.asList(35, 62, 86, 13, 38)),
			new ArrayList<>(Arrays.asList(28, 80, 19, 36, 75)),
			new ArrayList<>(Arrays.asList(98, 93, 47, 33, 57))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(22, 88, 35, 79, 85)),
			new ArrayList<>(Arrays.asList(98, 96, 89, 69, 17)),
			new ArrayList<>(Arrays.asList(37, 62, 57, 39, 1)),
			new ArrayList<>(Arrays.asList(99, 10, 55, 50, 71)),
			new ArrayList<>(Arrays.asList(65, 94, 67, 4, 63))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 7, 83, 51, 95, 98)),
			new ArrayList<>(Arrays.asList(56, 93, 62, 85, 9)),
			new ArrayList<>(Arrays.asList(72, 14, 44, 70, 67)),
			new ArrayList<>(Arrays.asList(42, 4, 65, 37, 54)),
			new ArrayList<>(Arrays.asList(47, 82, 1, 60, 55))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 0, 73, 60, 25, 64)),
			new ArrayList<>(Arrays.asList(90, 11, 93, 85, 89)),
			new ArrayList<>(Arrays.asList(80, 97, 86, 76, 96)),
			new ArrayList<>(Arrays.asList(43, 92, 88, 72, 44)),
			new ArrayList<>(Arrays.asList(62, 87, 81, 34, 49))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(47, 27, 89, 98, 68)),
			new ArrayList<>(Arrays.asList(86, 76, 14, 96, 17)),
			new ArrayList<>(Arrays.asList(21, 4, 41, 74, 29)),
			new ArrayList<>(Arrays.asList(18, 82, 33, 34, 20)),
			new ArrayList<>(Arrays.asList(30, 62, 95, 42, 51))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(45, 4, 70, 20, 53)),
			new ArrayList<>(Arrays.asList(66, 39, 43, 82, 1)),
			new ArrayList<>(Arrays.asList(54, 30, 68, 77, 42)),
			new ArrayList<>(Arrays.asList(61, 41, 65, 94, 35)),
			new ArrayList<>(Arrays.asList(25, 78, 22, 26, 46))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(70, 73, 44, 48, 61)),
			new ArrayList<>(Arrays.asList(69, 7, 85, 47, 89)),
			new ArrayList<>(Arrays.asList(91, 22, 12, 98, 11)),
			new ArrayList<>(Arrays.asList(25, 60, 58, 46, 54)),
			new ArrayList<>(Arrays.asList( 5, 37, 83, 62, 65))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(47, 62, 30, 70, 40)),
			new ArrayList<>(Arrays.asList(86, 9, 64, 61, 0)),
			new ArrayList<>(Arrays.asList(27, 63, 90, 88, 17)),
			new ArrayList<>(Arrays.asList(18, 71, 42, 33, 93)),
			new ArrayList<>(Arrays.asList(91, 14, 81, 4, 31))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(81, 7, 22, 94, 55)),
			new ArrayList<>(Arrays.asList(99, 90, 60, 9, 46)),
			new ArrayList<>(Arrays.asList(65, 2, 47, 1, 73)),
			new ArrayList<>(Arrays.asList(78, 76, 75, 19, 88)),
			new ArrayList<>(Arrays.asList(63, 51, 86, 56, 49))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(25, 27, 12, 22, 30)),
			new ArrayList<>(Arrays.asList(87, 75, 16, 4, 32)),
			new ArrayList<>(Arrays.asList(19, 73, 5, 20, 52)),
			new ArrayList<>(Arrays.asList(18, 6, 34, 94, 31)),
			new ArrayList<>(Arrays.asList(23, 96, 84, 26, 66))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(23, 69, 51, 35, 5)),
			new ArrayList<>(Arrays.asList(13, 76, 99, 89, 82)),
			new ArrayList<>(Arrays.asList(88, 3, 50, 54, 33)),
			new ArrayList<>(Arrays.asList(19, 59, 92, 84, 34)),
			new ArrayList<>(Arrays.asList(64, 80, 42, 40, 60))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(15, 91, 92, 60, 36)),
			new ArrayList<>(Arrays.asList(46, 40, 53, 34, 27)),
			new ArrayList<>(Arrays.asList(13, 35, 96, 16, 42)),
			new ArrayList<>(Arrays.asList( 4, 61, 81, 56, 24)),
			new ArrayList<>(Arrays.asList(85, 21, 7, 99, 20))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(32, 37, 19, 21, 28)),
			new ArrayList<>(Arrays.asList(66, 7, 96, 46, 88)),
			new ArrayList<>(Arrays.asList(23, 52, 25, 50, 22)),
			new ArrayList<>(Arrays.asList(53, 62, 34, 81, 27)),
			new ArrayList<>(Arrays.asList(98, 31, 14, 40, 49))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(23, 43, 71, 61, 12)),
			new ArrayList<>(Arrays.asList( 8, 94, 91, 74, 7)),
			new ArrayList<>(Arrays.asList(67, 2, 59, 77, 4)),
			new ArrayList<>(Arrays.asList(39, 18, 97, 41, 21)),
			new ArrayList<>(Arrays.asList(55, 15, 31, 9, 38))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(29, 69, 52, 16, 75)),
			new ArrayList<>(Arrays.asList(71, 15, 34, 79, 86)),
			new ArrayList<>(Arrays.asList(62, 57, 48, 44, 54)),
			new ArrayList<>(Arrays.asList(11, 32, 96, 13, 60)),
			new ArrayList<>(Arrays.asList(56, 77, 26, 68, 82))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(93, 57, 21, 94, 31)),
			new ArrayList<>(Arrays.asList(29, 4, 59, 24, 40)),
			new ArrayList<>(Arrays.asList(13, 99, 34, 96, 91)),
			new ArrayList<>(Arrays.asList(70, 55, 47, 62, 51)),
			new ArrayList<>(Arrays.asList(33, 32, 19, 69, 71))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(76, 80, 1, 57, 20)),
			new ArrayList<>(Arrays.asList(13, 28, 72, 27, 79)),
			new ArrayList<>(Arrays.asList(40, 21, 71, 37, 85)),
			new ArrayList<>(Arrays.asList(26, 12, 67, 33, 99)),
			new ArrayList<>(Arrays.asList(11, 41, 62, 18, 64))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(23, 22, 92, 69, 86)),
			new ArrayList<>(Arrays.asList(38, 79, 47, 56, 83)),
			new ArrayList<>(Arrays.asList(74, 46, 1, 95, 24)),
			new ArrayList<>(Arrays.asList(93, 71, 28, 54, 52)),
			new ArrayList<>(Arrays.asList(94, 51, 33, 57, 73))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(17, 96, 4, 81, 76)),
			new ArrayList<>(Arrays.asList(67, 20, 24, 21, 70)),
			new ArrayList<>(Arrays.asList(28, 77, 3, 74, 10)),
			new ArrayList<>(Arrays.asList(45, 78, 18, 7, 15)),
			new ArrayList<>(Arrays.asList( 8, 48, 27, 58, 13))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(51, 58, 59, 73, 35)),
			new ArrayList<>(Arrays.asList(13, 7, 92, 15, 98)),
			new ArrayList<>(Arrays.asList(75, 26, 1, 49, 24)),
			new ArrayList<>(Arrays.asList(91, 85, 44, 34, 74)),
			new ArrayList<>(Arrays.asList(64, 2, 20, 72, 90))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(46, 89, 50, 54, 79)),
			new ArrayList<>(Arrays.asList( 9, 60, 98, 36, 78)),
			new ArrayList<>(Arrays.asList(91, 16, 80, 92, 20)),
			new ArrayList<>(Arrays.asList(77, 69, 13, 76, 75)),
			new ArrayList<>(Arrays.asList(95, 41, 45, 3, 40))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(86, 7, 67, 20, 99)),
			new ArrayList<>(Arrays.asList(14, 18, 97, 70, 0)),
			new ArrayList<>(Arrays.asList(81, 27, 89, 30, 3)),
			new ArrayList<>(Arrays.asList(39, 37, 56, 42, 32)),
			new ArrayList<>(Arrays.asList(35, 71, 49, 8, 73))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(60, 67, 61, 6, 86)),
			new ArrayList<>(Arrays.asList(25, 41, 24, 29, 88)),
			new ArrayList<>(Arrays.asList(98, 3, 90, 56, 87)),
			new ArrayList<>(Arrays.asList(45, 22, 84, 70, 99)),
			new ArrayList<>(Arrays.asList(53, 59, 27, 26, 57))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(17, 4, 11, 41, 66)),
			new ArrayList<>(Arrays.asList(28, 39, 27, 54, 89)),
			new ArrayList<>(Arrays.asList( 3, 78, 37, 93, 29)),
			new ArrayList<>(Arrays.asList(95, 23, 86, 51, 40)),
			new ArrayList<>(Arrays.asList(75, 67, 71, 57, 92))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(60, 41, 91, 89, 52)),
			new ArrayList<>(Arrays.asList(68, 46, 83, 62, 1)),
			new ArrayList<>(Arrays.asList(18, 21, 72, 19, 35)),
			new ArrayList<>(Arrays.asList(55, 34, 11, 16, 75)),
			new ArrayList<>(Arrays.asList(32, 71, 61, 78, 50))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(27, 38, 70, 48, 93)),
			new ArrayList<>(Arrays.asList(16, 2, 80, 17, 63)),
			new ArrayList<>(Arrays.asList(97, 89, 55, 86, 85)),
			new ArrayList<>(Arrays.asList(54, 5, 41, 33, 60)),
			new ArrayList<>(Arrays.asList(51, 95, 12, 67, 37))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(72, 17, 74, 6, 41)),
			new ArrayList<>(Arrays.asList(53, 19, 8, 12, 92)),
			new ArrayList<>(Arrays.asList(39, 84, 82, 63, 48)),
			new ArrayList<>(Arrays.asList(22, 21, 87, 13, 32)),
			new ArrayList<>(Arrays.asList(40, 34, 64, 15, 31))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(75, 2, 46, 64, 99)),
			new ArrayList<>(Arrays.asList(26, 72, 79, 90, 76)),
			new ArrayList<>(Arrays.asList(85, 68, 10, 28, 67)),
			new ArrayList<>(Arrays.asList(20, 34, 81, 12, 83)),
			new ArrayList<>(Arrays.asList(92, 1, 65, 43, 71))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(49, 80, 85, 54, 9)),
			new ArrayList<>(Arrays.asList(31, 40, 22, 94, 51)),
			new ArrayList<>(Arrays.asList(12, 73, 43, 68, 98)),
			new ArrayList<>(Arrays.asList(78, 91, 70, 3, 28)),
			new ArrayList<>(Arrays.asList(47, 59, 69, 99, 62))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(46, 56, 28, 73, 20)),
			new ArrayList<>(Arrays.asList( 5, 29, 69, 68, 22)),
			new ArrayList<>(Arrays.asList(64, 12, 8, 52, 92)),
			new ArrayList<>(Arrays.asList(36, 44, 90, 72, 0)),
			new ArrayList<>(Arrays.asList(76, 48, 33, 86, 66))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(99, 61, 97, 17, 74)),
			new ArrayList<>(Arrays.asList(32, 52, 44, 42, 9)),
			new ArrayList<>(Arrays.asList(57, 67, 36, 41, 31)),
			new ArrayList<>(Arrays.asList(68, 1, 50, 22, 11)),
			new ArrayList<>(Arrays.asList(73, 12, 21, 48, 62))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(44, 53, 77, 88, 87)),
			new ArrayList<>(Arrays.asList(27, 99, 59, 98, 74)),
			new ArrayList<>(Arrays.asList(33, 66, 51, 14, 34)),
			new ArrayList<>(Arrays.asList(29, 30, 60, 49, 80)),
			new ArrayList<>(Arrays.asList(47, 84, 36, 12, 71))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(29, 89, 54, 59, 70)),
			new ArrayList<>(Arrays.asList(87, 65, 77, 38, 25)),
			new ArrayList<>(Arrays.asList(40, 17, 41, 9, 30)),
			new ArrayList<>(Arrays.asList(45, 27, 0, 5, 24)),
			new ArrayList<>(Arrays.asList(52, 8, 35, 68, 10))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(16, 41, 66, 87, 76)),
			new ArrayList<>(Arrays.asList(94, 70, 51, 48, 96)),
			new ArrayList<>(Arrays.asList(90, 73, 98, 89, 91)),
			new ArrayList<>(Arrays.asList( 4, 46, 30, 28, 63)),
			new ArrayList<>(Arrays.asList(68, 45, 37, 80, 57))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(19, 11, 46, 41, 14)),
			new ArrayList<>(Arrays.asList(94, 48, 66, 86, 9)),
			new ArrayList<>(Arrays.asList(42, 90, 56, 70, 21)),
			new ArrayList<>(Arrays.asList(95, 54, 74, 30, 87)),
			new ArrayList<>(Arrays.asList(81, 89, 49, 60, 34))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(18, 90, 79, 64, 98)),
			new ArrayList<>(Arrays.asList(27, 74, 59, 53, 11)),
			new ArrayList<>(Arrays.asList(96, 45, 17, 14, 23)),
			new ArrayList<>(Arrays.asList( 9, 60, 30, 42, 12)),
			new ArrayList<>(Arrays.asList(97, 21, 31, 5, 41))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(98, 63, 51, 92, 64)),
			new ArrayList<>(Arrays.asList(55, 30, 46, 22, 91)),
			new ArrayList<>(Arrays.asList( 8, 73, 61, 57, 67)),
			new ArrayList<>(Arrays.asList(37, 60, 49, 31, 10)),
			new ArrayList<>(Arrays.asList(80, 99, 77, 11, 82))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(52, 69, 77, 41, 8)),
			new ArrayList<>(Arrays.asList(94, 11, 78, 62, 28)),
			new ArrayList<>(Arrays.asList(91, 39, 96, 79, 3)),
			new ArrayList<>(Arrays.asList(44, 88, 37, 0, 47)),
			new ArrayList<>(Arrays.asList( 6, 80, 49, 98, 48))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(93, 2, 70, 26, 4)),
			new ArrayList<>(Arrays.asList(47, 8, 94, 12, 3)),
			new ArrayList<>(Arrays.asList(10, 7, 24, 40, 23)),
			new ArrayList<>(Arrays.asList(49, 84, 50, 56, 44)),
			new ArrayList<>(Arrays.asList(41, 53, 96, 1, 85))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(76, 78, 70, 24, 75)),
			new ArrayList<>(Arrays.asList(71, 19, 85, 77, 25)),
			new ArrayList<>(Arrays.asList(21, 44, 58, 45, 64)),
			new ArrayList<>(Arrays.asList(40, 38, 9, 50, 61)),
			new ArrayList<>(Arrays.asList(79, 42, 86, 37, 6))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(34, 39, 94, 84, 0)),
			new ArrayList<>(Arrays.asList(90, 80, 78, 54, 49)),
			new ArrayList<>(Arrays.asList(13, 81, 87, 60, 56)),
			new ArrayList<>(Arrays.asList(74, 59, 75, 41, 28)),
			new ArrayList<>(Arrays.asList(29, 67, 66, 44, 20))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(50, 66, 43, 39, 16)),
			new ArrayList<>(Arrays.asList(88, 94, 60, 70, 64)),
			new ArrayList<>(Arrays.asList(63, 80, 56, 69, 36)),
			new ArrayList<>(Arrays.asList(53, 48, 32, 22, 79)),
			new ArrayList<>(Arrays.asList(59, 77, 20, 30, 67))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(70, 56, 80, 12, 11)),
			new ArrayList<>(Arrays.asList(35, 55, 40, 71, 87)),
			new ArrayList<>(Arrays.asList(84, 27, 96, 46, 85)),
			new ArrayList<>(Arrays.asList(20, 23, 26, 29, 14)),
			new ArrayList<>(Arrays.asList(58, 37, 21, 75, 68))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(78, 23, 13, 37, 94)),
			new ArrayList<>(Arrays.asList(65, 44, 54, 43, 38)),
			new ArrayList<>(Arrays.asList(29, 60, 83, 1, 57)),
			new ArrayList<>(Arrays.asList(98, 2, 75, 12, 14)),
			new ArrayList<>(Arrays.asList(92, 25, 48, 9, 52))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(64, 37, 93, 48, 34)),
			new ArrayList<>(Arrays.asList(22, 81, 58, 5, 13)),
			new ArrayList<>(Arrays.asList(63, 80, 2, 67, 53)),
			new ArrayList<>(Arrays.asList(62, 52, 79, 41, 44)),
			new ArrayList<>(Arrays.asList(83, 75, 96, 91, 88))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 1, 54, 88, 45, 90)),
			new ArrayList<>(Arrays.asList(81, 78, 19, 8, 40)),
			new ArrayList<>(Arrays.asList(17, 74, 69, 87, 33)),
			new ArrayList<>(Arrays.asList( 9, 64, 85, 50, 71)),
			new ArrayList<>(Arrays.asList(92, 38, 65, 82, 41))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList( 2, 62, 96, 60, 81)),
			new ArrayList<>(Arrays.asList(51, 1, 34, 48, 25)),
			new ArrayList<>(Arrays.asList(78, 13, 74, 65, 42)),
			new ArrayList<>(Arrays.asList(46, 64, 57, 19, 72)),
			new ArrayList<>(Arrays.asList(85, 88, 53, 68, 76))
		))));

		game.add(new Board(new ArrayList<>(Arrays.asList(
			new ArrayList<>(Arrays.asList(57, 95, 40, 92, 27)),
			new ArrayList<>(Arrays.asList(65, 37, 42, 90, 9)),
			new ArrayList<>(Arrays.asList(17, 72, 78, 43, 45)),
			new ArrayList<>(Arrays.asList(87, 28, 48, 81, 79)),
			new ArrayList<>(Arrays.asList( 7, 4, 24, 67, 70))
		))));
        
        game.run();
        
        Board lastWinner = game.getLastWinner();
        
		System.out.println(processBoard(lastWinner, game.getLastDrawn()));
	}
	
	static int processBoard(Board board, Integer lastDraw) {
	    int sum = 0;
	    for (List<Integer> row : board.getNumbers()) {
	        for (Integer number : row) {
	            if (number != -1) {
	                sum += number;
	            }
	        }
	    }
	    
	    return sum * lastDraw;
	}
}

