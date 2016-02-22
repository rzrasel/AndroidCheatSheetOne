|------------------------------------------------------------|
public int getRandomInRange(int argMinVal, int argMaxVal) {
	if (argMinVal > argMaxVal) {
		int tempVal = argMaxVal;
		argMaxVal = argMinVal;
		argMinVal = tempVal;
	}
	Random random = new Random();
	int retVal = random.nextInt(argMaxVal - argMinVal + 1) + argMinVal;
	return retVal;
}
|------------------------------------------------------------|
