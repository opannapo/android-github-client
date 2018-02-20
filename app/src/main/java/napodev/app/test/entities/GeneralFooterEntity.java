package napodev.app.test.entities;

/**
 * Created by opannapo on 2/20/18.
 */

public class GeneralFooterEntity {
    private boolean hasnNextPage;
    private int currentPage;
    private int totalData;
    private int limit = 20;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasnNextPage() {
        hasnNextPage = (currentPage * limit < totalData);
        return hasnNextPage;
    }

    public void reset() {
        hasnNextPage = false;
        currentPage = 0;
        totalData = 0;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }
}
