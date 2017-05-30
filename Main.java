import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Created by calder on 5/15/17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            /*final SpreadSheet test = SpreadSheet.create(1,1,1);
            final test.getSheet(0);*/
            File file = new File("Piles.ods");
            final SpreadSheet SheetM = SpreadSheet.create(1, 1, 1);
            final Sheet sheet = SheetM.getSheet(0);
            sheet.setRowCount(200);
            sheet.setColumnCount(200);
            int test, Mid_row, Mid_col, Mid_value, New_Mid_value;
            int Up_row, Up_col, Left_row, Left_col, Right_row, Right_col;
            int Down_row, Down_col, Up_value, Down_value, Left_value, Right_value;
            int step = 2,counter =0, final_corner = counter + (step * 4), corner = 0, corner_type = 0;
            int empty = 0,found =0,run=0;
            boolean completed = false , repeated = false;
            int current_row, current_col, current_value = 0;
            int fail_safe= 0, fail_safe2 = 0;
            int side1 = 3, side2 = 1;
            Mid_row = 102;
            Mid_col = 33;
            current_row = Mid_row;
            current_col = Mid_col;

            corner = counter+ step;

            Up_row = current_col;
            Up_col = current_row - 1;

            Down_row = current_col;
            Down_col = current_row + 1;

            Left_row = current_col - 1;
            Left_col = current_row;

            Right_row = current_col + 1;
            Right_col = current_row;

            for(int i=0; i<1500; i++) {
                try{
                    sheet.setValueAt((Integer.valueOf(sheet.getCellAt(Mid_col,Mid_row).getTextValue())+1),Mid_col,Mid_row);
                }catch(Exception e){
                    sheet.setValueAt(1,Mid_col,Mid_row);
                }
                current_row = Mid_row;
                current_col = Mid_col;
                repeated = false;
                completed = false;
                fail_safe = 0;
                fail_safe2=0;
                counter =0;
                step = 2;
                corner = counter+ step;
                corner_type = 0;
                run=0;
                while(!repeated){
                    current_row = Mid_row;
                    current_col = Mid_col;
                    repeated = false;
                    completed = false;
                    fail_safe = 0;
                    counter =0;
                    step = 2;
                    corner = counter+ step;
                    corner_type = 0;
                    final_corner = 8;
                    side1=3;
                    side2=1;

                    if(run>=1 & found ==0){
                        repeated = true;
                        break;
                    }
                    run=0;
                    found=0;
                    if(fail_safe2>=10){
                        repeated = true;
                        System.out.print("Ended through failsafe2");
                    }
                    while (!completed & fail_safe <= 800) {
                        try {
                            current_value = Integer.valueOf(sheet.getCellAt(current_col, current_row).getTextValue());
                            empty=0;
                        } catch (Exception e) {
                            empty += 1;
                            current_value=0;
                            if (empty >= (step * 3 + 2)) {
                                completed = true;
                                empty = 0;
                                run +=1;


                                }


                            }

                    if (current_value >= 4) {
                        Up_col = current_col;
                        Up_row = current_row - 1;

                        Down_col = current_col;
                        Down_row = current_row + 1;

                        Left_col = current_col - 1;
                        Left_row = current_row;

                        Right_col = current_col + 1;
                        Right_row = current_row;
                        if (current_value >= 4) {
                            sheet.setValueAt(current_value - 4, current_col, current_row);
                            found= 1;
                        } else {
                            sheet.setValueAt(0, current_col, current_row);
                            found=1;
                        }

                        try {
                            Up_value = Integer.valueOf(sheet.getCellAt(Up_col, Up_row).getTextValue());
                            sheet.setValueAt(Up_value + 1, Up_col, Up_row);
                        } catch (Exception e) {
                            sheet.setValueAt(1, Up_col, Up_row);
                        }
                        try {
                            Down_value = Integer.valueOf(sheet.getCellAt(Down_col, Down_row).getTextValue());
                            sheet.setValueAt(Down_value + 1, Down_col, Down_row);
                        } catch (Exception e) {
                            sheet.setValueAt(1, Down_col, Down_row);
                        }
                        try {
                            Left_value = Integer.valueOf(sheet.getCellAt(Left_col, Left_row).getTextValue());
                            sheet.setValueAt(Left_value + 1, Left_col, Left_row);
                        } catch (Exception e) {
                            sheet.setValueAt(1, Left_col, Left_row);
                        }
                        try {
                            Right_value = Integer.valueOf(sheet.getCellAt(Right_col, Right_row).getTextValue());
                            sheet.setValueAt(Right_value + 1, Right_col, Right_row);
                        } catch (Exception e) {
                            sheet.setValueAt(1, Right_col, Right_row);
                        }


                    }
                    if (counter == final_corner) {
                        //counter += 1;
                        corner_type = 1;
                        empty = 0;
                        step += 2;
                        corner = counter + step;
                        current_col = current_col + 1;
                        side1+=2;
                        side2+=2;
                        final_corner = final_corner+((side1*2)+(side2+side2));


                    } else {
                        if (counter == corner) {
                            corner_type += 1;
                            corner += step;

                        }
                        switch (corner_type) {
                            case 1:
                                current_row = current_row - 1;
                                break;
                            case 2:
                                current_col = current_col - 1;
                                break;
                            case 3:
                                current_row = current_row + 1;
                                break;
                            case 4:
                                current_col = current_col + 1;
                                break;
                        }

                    }

                    if (counter == 0) {
                        // move right
                        current_col = current_col + 1;
                        corner_type = 1;

                    }
                    counter += 1;
                    if (corner_type >= 5) {
                        corner_type = 1;
                    }
                    fail_safe += 1;
                    if (fail_safe >= 5000) {
                        System.out.print("Ended throught fail_safe");
                        run+=1;
                        found =0;
                    }
                    }
                    fail_safe2+=1;
                }

            }

           /*try{
               Integer.valueOf(sheet.getCellAt(5,5).getTextValue());
           }catch(Exception e){
               System.out.print("failed");
           }
            for(int i=0;i<5;i++){
                Mid_value = Integer.valueOf(sheet.getCellAt(Mid_row,Mid_col).getTextValue());
                Up_value = Integer.valueOf(sheet.getCellAt(Up_row,Up_col).getTextValue());
                Down_value = Integer.valueOf(sheet.getCellAt(Down_row,Down_col).getTextValue());
                Left_value = Integer.valueOf(sheet.getCellAt(Left_row,Left_col).getTextValue());
                Right_value = Integer.valueOf(sheet.getCellAt(Right_row,Right_col).getTextValue());

                if(Mid_value>=4){
                    Mid_value =0;
                    sheet.setValueAt(0,Mid_row,Mid_col);
                    sheet.setValueAt(Up_value+1,Up_row,Up_col);
                    sheet.setValueAt(Down_value+1,Down_row,Down_col);
                    sheet.setValueAt(Left_value+1,Left_row,Left_col);
                    sheet.setValueAt(Right_value+1,Right_row,Right_col);
                }
                New_Mid_value = Mid_value +1;
                sheet.setValueAt(New_Mid_value,Mid_row,Mid_col);

            }*/

                //test = Integer.valueOf(sheet.getCellAt(1,1).getTextValue());
                //System.out.print(test);
       /* for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                sheet.setValueAt("Demo",i,j);
            }
        }*/
                if (file.exists()) {
                    file.delete();
                }
                SheetM.saveAs(file);
                OOUtils.open(file);

            }catch(FileNotFoundException e){
                e.printStackTrace();
            }



    }
}


