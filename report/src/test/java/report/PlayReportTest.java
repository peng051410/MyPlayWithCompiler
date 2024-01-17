package report;

import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2024/1/17 enjoy
 */
class PlayReportTest {

    @Test
    public void testReport1() {

        PlayReport report = new PlayReport();
        //report.parse("{=sales_amount/num_person}");

        //打印报表1
        String reportString = report.renderReport(ReportTemplate.sampleReport1(), TabularData.sampleData());
        System.out.println(reportString);

    }

    @Test
    public void testReport2() {

        PlayReport report = new PlayReport();
        //report.parse("{=sales_amount/num_person}");

        //打印报表2
        String reportString = report.renderReport(ReportTemplate.sampleReport2(), TabularData.sampleData());
        System.out.println(reportString);
    }

}
