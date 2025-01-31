import java.util.*;


public class PacificAtlanticWaterflow {

	public int[][] heights = {
		{1,2,3},
		{6,3,1},
		{3,1,2}
	};

	public int rows = heights.length;
	public int cols = heights[0].length;
    public boolean[][] pacificVisited = new boolean[rows][cols];
    public boolean[][] atlanticVisited = new boolean[rows][cols];

	public List<List<Integer>> pacificAtlantic() {

		// Do dfs for each cell bordering pacific
		// Left side
		for (int i = 0; i < rows; i++) {
			dfs(pacificVisited, heights[i][0], i, 0);
		}
		// Top side
		for (int j = 0; j < cols; j++) {
			dfs(pacificVisited, heights[0][j], 0, j);
		}

		// Do dfs for each cell bordering atlantic
		// Right side
		for (int i = rows-1; i >= 0; i--) {
			dfs(atlanticVisited, heights[i][cols-1], i, cols-1);
		}
		// Bottom side
		for (int j = cols-1; j >= 0; j--) {
			dfs(atlanticVisited, heights[rows-1][j], rows-1, j);
		}

		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (pacificVisited[i][j] && atlanticVisited[i][j]) {
					List<Integer> cell = new ArrayList<>();
					cell.add(i);
					cell.add(j);
					result.add(cell);
				}
			}
		}
		return result;
	}

	private void dfs(boolean[][] visited, int prev, int x_coord, int y_coord) {

		// Current cell does not exist within boundary
		if (x_coord >= rows || x_coord < 0 || y_coord >= cols || y_coord < 0) {
			return;
		}

		// Current cell already been visisted
		if (visited[x_coord][y_coord]) {
			return;
		}

		// Current cell is downhill from previous cell
		if (heights[x_coord][y_coord] < prev) {
			return;
		}

		visited[x_coord][y_coord] = 1;
		prev = heights[x_coord][y_coord];

		dfs(visited, prev, x_coord+1, y_coord);
		dfs(visited, prev, x_coord-1, y_coord);
		dfs(visited, prev, x_coord, y_coord+1);
		dfs(visited, prev, x_coord, y_coord-1);
	}
}