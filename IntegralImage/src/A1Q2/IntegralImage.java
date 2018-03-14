package A1Q2;

/**
 * 
 * Name: Yuxian Wang
 * ID: 215170418
 * 
 * 
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time. Uses O(n) memory,
 * where n is the number of pixels in the image.
 *
 * @author yuxian wang
 */
public class IntegralImage {

	private final int[][] integralImage;
	private final int imageHeight; // height of image (first index)
	private final int imageWidth; // width of image (second index)

	/**
	 * Constructs an integral image from the given input image.
	 *
	 * @author yuxian wang
	 * @param image
	 *            The image represented
	 * @throws InvalidImageException
	 *             Thrown if input array is not rectangular
	 */
	public IntegralImage(int[][] image) throws InvalidImageException {
		for(int i = 0; i < image.length; i++ ){
			while(image[0].length != image[i].length){
				throw new InvalidImageException();
			}
		}
		imageHeight = image.length;
		imageWidth = image[0].length;
		integralImage = image;
	}

	/**
	 * Returns the mean value of the rectangular sub-image specified by the top,
	 * bottom, left and right parameters. The sub-image should include pixels in
	 * rows top and bottom and columns left and right. For example, top = 1,
	 * bottom = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting at
	 * (top, left) coordinate (1, 1).
	 *
	 * @author yuxian wang
	 * @param top
	 *            top row of sub-image
	 * @param bottom
	 *            bottom row of sub-image
	 * @param left
	 *            left column of sub-image
	 * @param right
	 *            right column of sub-image
	 * @return
	 * @throws BoundaryViolationException
	 *             if image indices are out of range
	 * @throws NullSubImageException
	 *             if top > bottom or left > right
	 */
	public double meanSubImage(int top, int bottom, int left, int right) throws BoundaryViolationException, NullSubImageException {	
		if (top < 0 || bottom > imageHeight - 1 || left < 0 || right > imageWidth - 1) {
			throw new BoundaryViolationException();
		}
		if (top > bottom || left > right) {
			throw new NullSubImageException();
		} else {
			int count = 0;
			double sum = 0.0;
			for (int i = top; i <= bottom; i++) {
				for (int j = left; j <= right; j++) {
					sum += integralImage[i][j];
					count++;
				}
			}
			return sum / count; // dummy value - remove once coded.
		}
	}
}
