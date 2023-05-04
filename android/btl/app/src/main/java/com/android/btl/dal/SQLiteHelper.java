package com.android.btl.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.btl.model.Comment;
import com.android.btl.model.Post;
import com.android.btl.model.RViewPostItem;
import com.android.btl.model.Rating;
import com.android.btl.model.Views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "review_am_thuc.db";

    private static int DATABASE_VERSION = 2;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE posts("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "username TEXT,title TEXT,image TEXT,video TEXT, category TEXT,content TEXT,date TEXT)";
        db.execSQL(sql);

        String sql1 = "CREATE TABLE comments("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "username TEXT,id_post INTEGER, content TEXT, date TEXT)";
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE ratings("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "username TEXT,id_post INTEGER, rating REAL)";
        db.execSQL(sql2);

        String sql3 = "CREATE TABLE views("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "username TEXT,id_post INTEGER)";
        db.execSQL(sql3);
    }

    public long addPostItem(Post i) {
        ContentValues values = new ContentValues();
        values.put("id",i.getId());
        values.put("username", i.getUsername());
        values.put("title", i.getTitle());
        values.put("image", i.getImage());
        values.put("video",i.getVideo());
        values.put("category", i.getCategory());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("posts", null, values);
    }
    public long addPost(Post i) {
        ContentValues values = new ContentValues();
        values.put("username", i.getUsername());
        values.put("title", i.getTitle());
        values.put("image", i.getImage());
        values.put("video",i.getVideo());
        values.put("category", i.getCategory());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("posts", null, values);
    }

    public int deletePost(Post i){
        SQLiteDatabase sqLiteHelper=getWritableDatabase();
        String whereClause = "id = "+i.getId();
        return sqLiteHelper.delete("posts",whereClause,null);
    }

    public int updatePost(Post i){
        ContentValues values = new ContentValues();
        values.put("title", i.getTitle());
        values.put("image", i.getImage());
        values.put("video",i.getVideo());
        values.put("category", i.getCategory());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("posts", values,whereClause,whereArgs);
    }

    public int deleteAllPosts(){
        SQLiteDatabase sqLiteHelper=getWritableDatabase();
        return sqLiteHelper.delete("posts",null,null);
    }

    public void initDataPost() {
        deleteAllPosts();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        addPostItem(new Post(1,"vuducmanh012@gmail.com","Điểm danh các món ngon từ thịt ếch đồng quê","https://www.blogamthuc.com/images/post/2014/03/27/14//600-400-diem-danh-cac-mon-ngon-tu-thit-ech-c328.jpg","https://www.youtube.com/embed/77S7w3g6134","Món Ngon Việt Nam","     Từ những món ăn dân dã nơi làng quê, ếch đồng đã trở thành đặc sản, là nguyên liệu chính để chế biến nên đủ món ăn ngon như: ếch đồng chiên nước mắm, ếch chiên bơ, cháo ếch, ếch nấu cà ri hay lẩu… khiến nhiều người thích mê.\n" +
                "    Trong cái lạnh của thời tiết ngày cuối năm, không gì ngon miệng bằng khi được quây quần cùng bạn bè hoặc người thân bên nồi lẩu ếch lá giang bốc khói nghi ngút. Thịt ếch thường có mùi tanh, nên luôn được chế biến trước khi nấu chung với lẩu. Những con ếch được lột da, làm sạch, phần thân được tẩm ướp với gia vị, xào chín sơ. Nước dùng lẩu ếch có vị chua thanh của măng tươi và lá giang, tùy theo ý thích mà có thể ăn cay hoặc không. Ngoài ra, rau ăn kèm còn có thêm giá, đầu hành và bắp chuối bào mỏng.\n" +
                "     Ếch xào lăn có nguyên liệu gồm các loại rau củ như cà rốt, cà chua, ớt Đà Lạt… cùng hương thơm của cà ri và các loại gia vị khác nhau.\n" +
                "     Với ếch chiên nước mắm, từng phần thịt ếch được chiên vàng giòn thoang thoảng hương thơm của nước mắm. Khi ăn, thịt ếch giòn bên ngoài, mềm bên trong lại thấm đẫm gia vị vừa giúp bạn ngon miệng vừa không gây cảm giác ngấy.\n" +
                "    Ếch chiên bơ với hương thơm nức mũi cũng khiến người ăn thích mê. Chế biến món ăn này khá đơn giản, phần thịt ếch sau khi làm sạch được cho vào chảo chiên vàng giòn với bơ. Trong quá trình chiên, phần bơ thấm vào từng thớ thịt giúp cho phần thịt trở nên mềm, thơm và béo ngậy. Thêm một chén tương ớt hoặc nước tương khi ăn kèm món này giúp cho món ăn trở nên đậm đà hơn.\n" +
                "     Ếch xào sa tế được người ăn ưa thích nhờ hương vị vừa thơm vừa cay nồng đặc trưng. Không chỉ có vậy, chính nhờ xào với sa tế nên ếch đã bớt đi được mùi tanh.@ Nếu thích ăn bánh mì, bạn khó có thể bỏ qua món cà ri ếch đầy hấp dẫn. Phần nước dùng sánh, vàng ươm màu cà ri thơm nồng thật thơm ngon. Một điều cần lưu ý là phải dùng món này khi còn nóng mới cảm nhận được vị thơm ngon của nó. Nếu dùng nguội, thịt ếch vừa mềm, vừa bở lại có mùi tanh không ngon.@ Khi đã thỏa thích với những món ăn chơi hoặc muốn tìm cho mình một món ăn ấm bụng C2khi đêm về, bạn có thể gọi cho mình một phần cháo ếch để thưởng thức. Phần thịt ếch được làm sạch, ướp sơ với các loại gia vị như nước tương, hắc xì dầu ngọt, đường, dầu hào, hành tím, rượu mai quế lộ… Hành tỏi được phi thơm, tiếp đến cho ếch vào xào săn rồi cho ít nước lọc vào đun sôi. Nêm lại gia vị, cho vào nồi đất tiếp tục đun đến khi nước trong nồi sánh lại, thịt ếch chín mềm thấm đậm gia vị là được. Ăn kèm là một bát cháo trắng dậy mùi lá dứa thơm ngon.\n" +
                "      Ngoài những món ăn chơi kể trên, những món ếch còn lại bạn cũng không nên bỏ qua như: ếch chiên ăn kèm chà bông; ếch chiên trứng muối… Nếu thích những món ăn từ ếch, bạn có thể ghé đến các địa chỉ sau tại TP HCM: 68/28 Nguyễn Cư Trinh, quận 1; 290 Phan Xích Long P.2, quận Phú Nhuận; 2A Nguyễn Thị Minh Khai, phường Đa Kao, quận 1. Các món ếch đều có giá từ 60.000 đồng, riêng lẩu có giá 90.000 đồng. Các quán này đều bán từ khoảng 11h đến 22h30 hằng ngày.","10/02/2018"));
        addPostItem(new Post(2,"vuducmanh013@gmail.com","Cách làm gà nướng chuẩn vị Tây Bắc, ai ăn cũng muốn học cách làm cho bằng được","https://cdn.24h.com.vn/upload/1-2021/images/2021-03-17/picture-7-1615971747-180-width660height529.jpg","https://www.youtube.com/embed/m8avJtaES_g","Món Ngon Việt Nam","Ăn gà nướng mắc khén mà không ăn kèm với chẩm chéo thì không tròn vị núi rừng Tây Bắc. Chỉ vài cách đơn giản, bạn có thể làm món gà nướng chấm chẩm chéo cho gia đình thưởng thức.@ Nguyên liệu:\n" +
                "  -1 con gà mái tầm 1,2 tới 1,5 kg\n" +
                "- Mắc khén: 3 muỗng\n" +
                "- Lá chanh tươi\n" +
                "- 10 trái ớt khô nguyên\n" +
                "- 1 củ gừng nhỏ\n" +
                "- 4 cây sả\n" +
                "- 2-3 muỗng muối rang hoặc muối gia vị\n" +
                "- 1 muỗng bột ngọt\n" +
                " Cách thực hiện:\n" +
                " Bước 1:  Nướng ớt khô.\n" +
                "Bước 2: Sao khô/ rang khô lá chanh và rang mắc khén cho đến khi ngửi thấy mùi thơm\n" +
                "Bước 3: Sả và gừng cắt khúc để chuẩn bị xay nhuyễn hỗn hợp ướp gà\n" +
                "Bước 4: Cho hỗn hợp vào cối xay thịt để xay nhuyễn hỗn hợp bao gồm: ớt khô đã nướng, lá chanh đã sao, mắc khén đã rang, sả, gừng, 3 muỗng muối, 1 muỗng bột ngọt. \n" +
                "Lưu ý: Không sử dụng đường hay mật ong, dầu hào, nếu hỗn hợp khô, khó xay, có thể cho vào 1 ít nước. Không có máy xay, thì cho vào cối giã.\n" +
                "Bước 5: Món này mình dùng tre để nẹp gà, bạn nào nướng lò có thể để nguyên con, chẻ đôi hoặc chặt khúc sẵn nha. Gà ướp khoảng 30 phút sau đó mang đi nướng.\n" +
                "Lưu ý: \n" +
                "- Nếu nướng bằng lò gia đình: Có thể nướng ở nhiệt độ thấp trước để gà có thể chín dần rồi sau đó đẩy nhiệt độ lên cao để làm vàng da. Theo kinh nghiệm với lò nướng đối lưu 2 thanh nhiệt khoảng 160-180 độ C trong tầm 30 phút sau đó chỉnh lên 200 độ thêm khoảng 15-20 phút nữa để phần da gà được vàng.\n" +
                "Lưu ý đối với các bạn nướng than: Phải để than cháy thật đỏ mới bắt đầu nướng để không bị hôi khói.\n" +
                "Bước 6: Làm chẩm chéo\n" +
                "- 5 trái ớt nướng\n" +
                "- 10 lá chanh sao/ rang khô\n" +
                "- 3 muỗng mắc khén đã rang\n" +
                "- Vài tép tỏi\n" +
                "- 2 muỗng muối rang & 1 muỗng bột ngọt.\n" +
                "Lưu ý, chẩm chéo không dùng sả  và gừng.\n" +
                "Cho hỗn hợp và cối xay để xay nhuyễn hỗn hợp hoặc cho vào cối để giã nhuyễn. Bạn nào muốn dùng chẩm chéo ướt có thể pha xíu nước nóng rồi trộn đều lên là xong. Bạn nào dùng chẩm chéo khô thì dừng lại ở bước xay nhuyễn nha.","24/11/2022"));
        addPostItem(new Post(3,"vuducmanh014@gmail.com","Bí quyết làm nem chuẩn vị Hà Nội, công thức được hàng nghìn người tán đồng, chia sẻ","https://cdn.24h.com.vn/upload/1-2021/images/2021-01-09/1610191396-1341102351930181713799111908225318878022951n-16099031978742087538743.jpg","https://www.youtube.com/embed/vXr7E4G4rBc","Món Ngon Việt Nam","Nem rán được coi là món \"quốc hồn quốc túy\" của Việt Nam nhưng lại nhận được nhiều sự tranh luận nhất.@Sau khi tranh luận gay gắt vì món nem rán thế nào là chuẩn vị Hà Nội, chị Duyên Hoàng đã chia sẻ công thức làm nem, theo chị là chuẩn theo cách Hà Nội nhất. Ngay lập tức, cách làm nem của chị Hoàng đã nhận được sự đồng tình của đông đảo chị em.@Nguyên liệu (cho khoảng 70 - 80 cái nem):\n" +
                "- Vỏ nem (chị Hoàng chọn vỏ bánh đa nem của Thổ Hà, vỏ dai, dẻo không cần thêm lót); 1kg thịt lợn vai; 2 lạng tôm nõn tươi hoặc khô; 3 lạng tôm hấp sơ bóc vỏ cắt nhỏ xếp nốt vào âu.@\n" +
                "- Nếu làm nem thịt không nhân tôm thì bỏ tôm và thêm 3 lạng thịt.\n" +
                "- 0,5 lạng mộc nhĩ, 0,5 lạng nấm hương ngâm nước cho mềm rửa sạch thái dài mỏng. \n" +
                "- Miến 1.5 lạng\n" +
                "- 1 củ đậu to 5 lạng; 2 quả xu xu hoặc su hào (3 Lạng); 2 củ cà rốt ( 2 Lạng); 1 củ hành tây 2 lạng; 1 lạng giá đỗ; ít hành hoa; trứng khoảng 10 quả.\n" +
                "Cách làm:\n" +
                "Bước  1: Sơ chế\n" +
                "Thịt lợn băm nhuyễn. Chị Hoàng chia sẻ mỗi lần làm nem là mỗi lần người nhà \"to tay\" vì phải băm thịt, băm thì băm phải nhỏ đều khi nào thấy thịt nhỏ đều quện lại thì dừng, (mà xay thì chỉ xay 1 lần thôi chứ không được xay nhỏ quá sẽ bết nhân)... xếp thịt vào âu.\n" +
                "Tôm khô ngâm mềm băm nhỏ trộn vào thịt trong âu. Khi xay hay băm thịt thì xay luôn tôm vào.\n" +
                "Còn tôm nõn tươi thì chị Hoàng chia nửa chín nửa sống vì tôm sống quện thịt rất thơm còn tôm chín lại có độ giòn thơm khi cắn miếng nem.\n" +
                "Mộc nhĩ, nấm hương ngâm nước cho mềm rửa sạch thái dài mỏng. Mộc nhĩ ngâm sẽ lâu hơn khoảng 40p, nấm hương thì 20p nước hơi ấm là mềm. Hai nguyên liệu này ngâm trước nếu có ý định làm nem.\n" +
                "Miến ngâm nước lạnh 10p thôi không mềm là nát nhân, thái nhỏ bằng 1 đốt ngón tay.\n" +
                "Các loại rau thái mỏng sợi. Rau chị Hoàng thường thái chứ không bào vì rau thái sẽ mềm và ngọt hơn. Giá đỗ để riêng trộn vào sau cùng tránh nát nhân. Thái chút hành lá vào nhân cho thơm chứ không cho rau húng hay rau mùi vào nhân.\n" +
                "Bước 2: Trộn nhân\n" +
                "Đổ tôm thịt vào tô cho 1 thìa bột nêm, 1 thìa bột canh, 1 thìa mì chính, 1 thìa cafe hạt tiêu. Đeo găng trộn đều, rồi tiếp tục cho giá đỗ vào trộn.\n" +
                "Trứng chỉ lấy lòng đỏ. Nếu trứng chuẩn thơm gà ri ta thì cả lòng trắng lẫn đỏ không sao. Khi nào gói nem thì hãy trộn trứng vì trứng sẽ làm nhân chảy nước. Chị Hoàng thường chia nhân làm nhiều phần, cuốn đến đâu chia trứng trộn đều đến đấy.@Bước 3: Gói nem và rán\n" +
                "Gói nem tuỳ hoa tay mỗi người thì độ dài của nem sẽ bằng 1/2 lá nằm chính giữa cách đều 2 bên gấp chặt tay 1 lượt xong gấp 2 bên lại cuốn hết độ dài. Chị Hoàng để khăn ẩm sạch dưới lá nem rồi vuốt lá miết vào khăn cho lá mềm chứ không vuốt nước hay dấm, khăn khô lại đem đi vò vắt.\n" +
                "Gói đến đâu rán luôn đến đó dầu ăn thật già mới cho nem vào rồi hạ lửa nhỏ vừa nem chín 70% lật tiếp mặt trên xong để ra rổ có giấy thấm dầu.\n" +
                "Sau khâu này để nguội cấp đông. Đến khi cần lấy ra 1 lúc cho vào nồi chiên không dầu phun tí dầu nướng 180 độ 10p là giòn. \n" +
                "Rán lần 2 chị Hoàng thường quết 1 lớp bia lên mới rán thì màu vàng đẹp và giòn.\n" +
                "Bước 4: Nước chấm\n" +
                "Đu đủ hoặc su hào non, cà rốt thái hoa mỏng nhỏ như đốt tay bóp muối thật kĩ để 10p rồi trần qua nước sôi để yên 3p cho chín xíu đỡ ngái rồi rửa lại ngay thật sạch với nước lạnh thật lạnh để giòn lại. Trộn với giấm và đường,để ngấm tối thiểu 20p.\n" +
                "Tỏi đập dập băm nhỏ, ớt bỏ hạt thái nhỏ.\n" +
                "5 bát nước sôi đang nóng già 1 bát đường khuấy đều cho tan, tiếp theo cho 1 bát mắm 1 bát dấm, thêm 1 thìa cơm xì dầu l. Hỗn hợp nguội 80% mới cho tỏi, ớt. Sau cùng đổ dưa góp vào. Tuyệt đối không được đổ nước ngâm vào nếu như nêm thấy vừa rồi, thiếu chua hãy cân nhắc, thêm 1 thìa cafe hạt tiêu vào khuấy nhẹ 1 lần.\n" +
                "Rau sống gồm xà lách xoăn, kinh giới, tía tô, mùi....","15/03/2023"));
        addPostItem(new Post(4,"vuducmanh015@gmail.com","Phở khô - món ăn nhất định phải thử khi đến phố núi Gia Lai","https://anh.24h.com.vn/upload/4-2017/images/2017-12-27/Pho-kho---mon-an-nhat-dinh-phai-thu-khi-den-pho-nui-Gia-Lai-pho-kho---mon-an-nhat-dinh-phai-thu-khi-den-pho-nu-1514373642-926-width1600height1015.jpg","https://www.youtube.com/embed/N_jwR-Tf7Jw","Món Ngon Việt Nam","Không như các loại phở thông thường có phở và nước dùng cùng chung một tô, phở khô Gia Lai luôn được phục vụ với hai tô, một đựng phở, một là nước lèo.@ Có thể nói nếu bạn đến phố núi Gia Lai mà chưa thưởng thức phở khô thì quả là một thiếu sót bởi món ăn này là đặc sản nổi tiếng bậc nhất làm nên ẩm thực của Gia Lai. Du khách đến đây đều được nghe giới thiệu về món ăn này và tiếng tăm của nó còn được lan truyền sang các tỉnh lân cận như Kon Tum, Đắk Lắk, gây thương nhớ cho bất kỳ ai khi đã một lần thưởng thức.@ Nguyên liệu để chế biến món ăn này cũng có nét giống như món phở trộn ở một số tỉnh thành, nhưng điều đặc biệt hơn là hương vị rất đậm đà, được phục vụ riêng bằng hai tô, tô đựng phở khô trộn và một tô nước lèo.\n" +
                "Điểm khác biệt ở đây là bánh phở có vị dẻo dai, sợi nhỏ, làm từ bột gạo nhưng do cách chế biến mà khi trụng vào nước sôi, phở không bị mềm nát và rất dai, thơm ngon. Tô phở được dọn ra với lớp bánh phở trắng, bên trên là một lớp thịt gà, thịt lợn đã băm được xào thơm cùng với hành phi và rau thơm. Quyết định đến độ ngon của bát phở trộn này chính là phần nước lèo. Nước được ninh từ xương ống, thịt bò, thịt gà, gân bò... ninh liu riu trên bếp lửa nên rất ngọt và trong.@\n" +
                "Ở Pleiku có hai quán phở khô nổi tiếng nhất là phở khô Ngọc Sơn (quán cũ ở 15 Nguyễn Thái Học, một quán ở đầu dốc cầu Hội Phú) và phở Hồng ở đường Nguyễn Văn Trỗi. Riêng phở khô Hồng ở nổi tiếng cả ở Sài Gòn, mở được 2 cơ sở và được rất nhiều thực khách Sài thành yêu thích.@\n" +
                "Nếu gọi phở khô gà, người ta sẽ bày thịt hoặc lòng gà xé hay xắt nhỏ lên bánh phở ở tô thứ nhất, rưới lên phía trên là thịt heo bằm nhỏ hoặc tóp mỡ. Còn nếu ăn với thịt bò tái, xương heo, bò viên thì các thành phần này nằm trong tô thứ hai. Ngập trong tô nước lèo là thịt bò tái, xương heo, hoặc bò viên, nổi lên trên là hành ngò xắt nhỏ.@Phở khô Gia Lai cũng có cách ăn riêng, phải đúng kiểu mới cảm nhận được hết cái ngon của món ăn. Bạn có thể gia giảm độ mặn nhạt bằng tương nâu làm từ đậu nành và đường vàng cùng xì dầu tùy vào cảm nhận của mỗi thực khách. Rau ăn kèm với phở khô cũng đơn giản chỉ có xà lách, húng quế và giá.@\n" +
                "Khi ăn, bạn sẽ cảm nhận được độ ngon của tô phở khô với sợi phở được làm từ bột gạo nhưng lại có độ dai nhất định, thấm gia vị và không bị nát, quyện trong các nguyên liệu là các loại thịt được chế biến vừa miệng, đậm đà.",date));
        addPostItem(new Post(5,"vuducmanh016@gmail.com","Phở – Một thứ quà đặc biệt của Hà Nội","https://digifood.vn/blog/wp-content/uploads/2021/05/pho-ngon-ha-noi-3-1-1024x1024.jpg","https://www.youtube.com/embed/594Su15nu48","Món Ngon Việt Nam","Hà Nội không chỉ kiều diễm, trang hoàng trong mắt du khách với những con phố cổ kính, những mặt hồ lung linh, huyền thoại hay mùa thu nồng nàn hoa sữa… Hà Nội còn dạt dào cùng những món ăn ngon, những dư vị đã đi vào huyền thoại. Phở là một trong những niềm tự hào của người Hà Nội.\n" +
                "Phở ngon ngọt không chỉ khi thưởng thức mà còn ngon ngọt khi được giãi bày trên những trang viết của nhiều nhà văn nổi tiếng như: Thạch Lam, Nguyễn Tuân hay Vũ Bằng… Không biết phở có tự bao giờ, chỉ biết một điều, dư vị phở để lại trong cảm nhận của mỗi người là vô cùng đa dạng.\n" +
                "Trong “Băm sáu phố phường” nhà văn Thạch Lam đã Viết: Phở là một thứ quà đặc biệt của Hà Nội, không phải chỉ riêng Hà Nội mới có, nhưng chính là vì chỉ ở Hà Nội mới ngon. Phở ngon phải là phở “cổ điển”, nấu bằng thịt bò, “nước dùng trong và ngọt, bánh dẻo mà không nát, thịt mỡ gầu giòn chứ không dai, chanh ớt với hành tây đủ cả”, “rau thơm tươi, hồ tiêu bắc, giọt chanh cốm gắt, lại điểm thêm một ít cà cuống, thoảng nhẹ như một nghi ngờ”. Chỉ có những con người yêu và gắn bó với Hà Nội mới cảm nhận được về một đặc sản của Hà Nội sâu sắc đến vậy.\n" +
                "Phở trong lòng người Hà Nội được trân trọng như một món quà. Người Hà Nội coi phở như bữa ăn trong ngày, có thể dùng sáng, trưa hay tối. Nước dùng của phở được làm từ nước ninh của xương bò: xương cục, xương ống và xương vè. Thịt dùng cho món phở có thể là thịt bò, hay thịt gà. Bánh phở phải mỏng và dai mềm, gia vị của phở là hành lá, hạt tiêu, giấm ớt, lát chanh thái. Phở luôn phải ăn nóng mới ngon, người Hà Nội còn ăn phở kèm với quẩy. Để có được những bát phở ngon còn tùy thuộc vào rất nhiều yếu tố như kinh nghiệm và bí quyết truyền thống của người nấu phở.\n" +
                "Phở Hà Nội, sự tinh túy xuất phát từ những đôi bàn tay tài hoa \n" +
                "Hà Nội có rất nhiều thương hiệu phở nổi tiếng mang hương vị đặc trưng như: phở Lý Quốc Sư – đây là thương hiệu phở đã được khẳng định từ lâu. Phở ở đây rất ngon và có nhiều hương vị và có nhiều loại phở cho khách lựa chọn như: tái, chín, hay tái nạm gầu…. tùy vào sở thích của khách hàng. Đặc điểm của thương hiệu “phở Lý Quốc Sư” là nước dùng của phở đậm đà và rất thơm do cách chế biến và lựa chọn gia vị người nấu. Hay như phở Thìn, để có một bát phở ngon, ngoài việc chế ra nước phở vừa trong, vừa ngọt, vị ngọt sâu của xương ninh kèm gia vị, phở Thìn còn chú ý đến công đoạn xào thịt, chan phở. Thịt bò được xào trên một lò lửa nhiệt độ cao, mỡ đun nóng già, lửa bùng lên, đảo thật nhanh, thịt bò sẽ tái tức thì cho màu đẹp và ăn rất ngọt…”\n" +
                "Nguyễn Tuân, nhà văn của những tác phẩm tùy bút xuất sắc vang bóng một thời cũng có những câu chuyện rất đời thường gắn liền với phở Hà Thành. Có một lần nhà văn đi ăn phở, một người yêu thích nhận ra nhà văn bước lại chào nhưng Nguyễn Tuân vẫn cắm cúi vào bát phở. Để chắc chắn cho sự không nhầm lẫn của mình, người kia kiên trì chờ đợi. Tô phở hết nhà văn Nguyễn Tuân ngẩng mặt lên bảo “Tôi đang thưởng thức nên không trả lời, anh thứ lỗi”. Phở với Nguyễn Tuân là một cái gì đó rất đặc biệt, nhà văn đã không lỡ dùng từ “ăn” mà dùng từ “thưởng thức” khi trả lời. Trong một tùy bút xuất sắc về phở, ông đã cho phở có một “tâm hồn”, phở là “một miếng ăn kỳ diệu của người Việt Nam chân chính”.\n" +
                "Từ hương vị cho tới màu sắc của phở như một bức hoạ lập thể hơi bạo màu nhưng đẹp mắt, dậy lên hương vị, đánh thức tất thảy khả năng vị giác, khứu giác của người ăn, khiến con người ta có cảm giác đang được hưởng cái tinh tế của đất trời và con người hợp lại. Chỉ húp một tý nước thôi đã thấy tỉnh người. Thịt thì mềm, bánh thì dẻo, thỉnh thoảng lại thấy cái cay dịu của gừng, cái cay nồng của ớt, cái thơm nhè nhẹ của rau thơm. Tất cả cứ ngọt lừ, ngọt một cách hiền lành, êm nhẹ mà chân thật.\n" +
                "Phở Hà Nội, cái ngon của tất thảy những chất liệu đời thường ẩm thực Việt, được những đôi bàn tay tài hoa của người Hà Nội làm thành tác phẩm!",date));
        addPost(new Post(6,"vuducmanh017@gmail.com","Bánh căn hải sản","https://owa.bestprice.vn/images/articles/uploads/15-mon-an-dac-san-nha-trang-ngon-nhat-khong-the-bo-qua-5eb128aae5ed2.jpg","https://www.youtube.com/embed/TP-jZzebO7g","Món Ngon Việt Nam","Bánh căn là món ăn quen thuộc được mang từ Đà Lạt về Nha Trang, tuy không phải là món đặc sản chính gốc nhưng bán căn Nha Trang là món ăn bạn nhất định phải thử khi đến với thành phố biển. Khác với món bánh căn thông thường không có nhân hoặc có nhân từ thịt heo, trứng cút,… thì bánh căn Nha Trang có nhân từ hải sản. Bánh căn hải sản từ mực, tôm, ghẹ hoặc bạch tuộc được ăn kèm nước chấm nắm nêm, mắm cá hoặc nước mắm ngọt mang đặc trưng của miền biển sẽ đem đến cho du khách cảm nhận vị ngon lạ miệng khó quên. Địa chỉ đáng tin cậy để thưởng thức món bánh căn hải sản này là ở:\n" +
                "- Bánh căn Cô Tư, đường Tháp Bà (7A), giá cho một suất là 20.000 – 50.000đ/suất, quán mở cửa từ 10h – 21h hàng ngày.\n" +
                "- Bánh căn 151 Hoàng Văn Thụ, mở cửa buổi sáng từ 6h – 11h, giá từ 20.000 – 30.000đ/suất.","10/10/2022"));
        addPost(new Post(7,"vuducmanh012@gmail.com","Thịt bò nướng Lạc Cảnh","https://owa.bestprice.vn/images/articles/uploads/15-mon-an-dac-san-nha-trang-ngon-nhat-khong-the-bo-qua-5eb12a25a1fab.jpg","https://www.youtube.com/embed/Ew7_TyGBU54","Món Ngon Việt Nam","Trong cẩm nang du lịch của bạn sẽ không thể thiếu món này khi du lịch Nha Trang, đây cũng là một trong số những đặc sản hớp hồn các du khách nước ngoài bởi sự đặc biệt trong cách chế biến cũng như hương vị của nó. Thịt bò tươi được tẩm ướp bằng những thìa mật ong ngọt ngào cùng một số gia vị khác, khi nướng tạo lên mùi thơm lạ thường so với thịt nướng thường, món này được gói ăn kèm với rau sống rất ngon và độc đáo… Bò nướng Lạc Cảnh có màu đỏ bắt mắt, khi ăn sẽ thấy thịt bò ngọt, có vị thơm và mặn từ gia vị ướp thịt, ăn với rau sống sẽ bớt được độ nóng của miếng thịt, ngoài ra bạn còn có thể ăn kèm với bánh mì cũng sẽ thấy rất thú vị. Nhà hàng Lạc Cảnh ở số 44 Nguyễn Bỉnh Khiêm có thâm niên lên đến 40 năm là địa chỉ chất lượng nhất cho món ăn này với giá bình dân chỉ từ 90.000 – 150.000đ/người sẽ thỏa sức ăn no của các du khách ghé đến.","16/03/2022"));
        addPost(new Post(8,"vuducmanh013@gmail.com","Món Bruschetta của nước Italia","https://cattour.vn/images/upload/images/Du-lich-chau-au/mon-an-chau-au/rosti-4.png","https://www.youtube.com/embed/HFkFQ8NV-VE","Món Ngon Châu Âu","Bruschetta là một món ăn nhẹ, thường được dùng làm món khai vị của nước Ý. Món ăn này sử dụng nguyên liệu chính là bánh mì nướng kết hợp với nước sốt và nhiều loại thực phẩm khác. Thông thường, Bruschetta sẽ bao gồm bánh mì nướng cắt khoanh phết tỏi, cà chua băm nhỏ hoặc cắt lát mỏng kết hợp với dầu oliu hoặc lá húng quế. Ăn Bruschetta bạn sẽ cảm nhận được vị giòn, thơm và  ngậy béo hài hòa của các nguyên liệu.\n" +
                "Bruschetta là món ngon nổi tiếng khắp châu Âu. Tùy theo sở thích mà bạn có thể thêm thịt xông khói, phô mai… vào bữa ăn của mình.",date));
        addPost(new Post(9,"vuducmanh014@gmail.com","Spaghetti của Ý (mì Ý)","https://cattour.vn/images/upload/images/Du-lich-chau-au/mon-an-chau-au/Spaghetti-2.png","https://www.youtube.com/embed/TdHXWn5Ogf0","Món Ngon Châu Âu","Ẩm thực Ý không chỉ có pizza mà còn có Spaghetti - một loại thực phẩm thiết yếu của nền ẩm thực Ý truyền thống. Spaghetti là một loại pasta (mì) sợi dài, nhỏ, hình trụ được làm từ bột mì (Spaghetti của Ý được làm từ bột mì semolina làm từ lúa mì cứng) và nước. \n" +
                "Ban đầu sợi mì spaghetti được làm khá dài, nhưng dần dần nhiều sợi mì có độ dài ngắn hơn trở nên được ưa chuộng trong nửa sau thế kỉ 20 và ở thời điểm hiện tại thì spaghetti chủ yếu dài khoảng 25 – 30 cm.\n" +
                " Trong ẩm thực Ý, spaghetti thường được ăn với xốt cà chua. Các loại xốt này được chế biến từ cà chua với rất nhiều loại rau gia vị như là oregano và húng (Ocimum basilicum), dầu ô liu, thịt và rau... Người ta cũng thường rắc thêm một số loại pho mát xay, chẳng hạn như Parmesan, Pecorino Romano hay Asiago. Bên cạnh đó, Spaghetti còn có rất nhiều cách chế biến khác nhau như Spaghetti hải sản, Spaghetti sốt kem...\n" +
                "Cũng giống như pizza, spaghetti hiện cũng là một món ăn phổ biến trên thế giới, bạn có thể thưởng thức món ăn này ở bất cứ đâu nhưng nếu đến Ý thì đừng bỏ lỡ cơ hội thưởng thức Spaghetti Ý chính tông nhất nhé!","01/01/2023"));
        addPost(new Post(10,"vuducmanh015@gmail.com","ốc sên nướng trên đất Pháp","https://massageishealthy.com/wp-content/uploads/2018/04/cac-mon-an-chinh-cua-nguoi-chau-au-thuc-don-mon-ngon-chau-au-2.jpg","https://www.youtube.com/embed/fpunq_2gvu4","Món Ngon Châu Âu","Bạn đã bao giờ nghĩ một loài vật “vô dụng” trong vườn nhà mình lại trở thành món ăn thượng hạng trong những nhà hàng lớn ở châu Âu? Đó là sự thực bởi lẽ ở Pháp, ốc sên không hề vô dụng mà lại là đặc sản khiến bao người ngạc nhiên rồi sau đó là mê mẩn.\n" +
                "Ốc sên được làm sạch, nướng cùng bơ, tỏi, mùi tây và một vài loại gia vị đắt đỏ khác như xạ hương, hạt thông, dùng kèm rượu vang. Đặc biệt, công đoạn làm sạch vỏ và ruột ốc sên rất phức tạp, có khi phải mất 2 – 3 ngày mới hoàn thành.@Chính vì thế, ốc sên dẫu không phải là nguyên liệu sang trọng, song khi được đặt lên bàn tiệc lại trở thành món hảo hạng chỉ dành cho những người sành ăn.\n" +
                "Sỡ dĩ ốc sên được xếp vào danh sách hạng sang bởi lẽ nó chứa nhiều chất dinh dưỡng rất bổ cho cơ thể con người. Chỉ tính riêng phần thịt ốc đã chiếm 15% protein và rất ít chất béo. Ngoài ra còn có tác dụng chữa các bệnh về khớp, đau lưng…\n" +
                "Không phải bỗng nhiên mà món Âu ngon nổi tiếng như sunday roast và ốc sên nướng, những món ăn vốn dĩ sử dụng nguyên liệu không quá xa lạ hay thượng hạng lại trở thành đặc trưng của một quốc gia.\n" +
                "Chính tài năng, sự sáng tạo và kĩ năng đỉnh cao của người đầu bếp đã góp phần đưa ẩm thực Anh hay Pháp vươn xa và được yêu thích, ngưỡng mộ trên toàn thế giới.\n" +
                "Vẫn còn rất nhiều món ăn châu Âu hấp dẫn và độc đáo khác có thể bạn chưa từng được nghe qua. Như một đầu bếp nổi tiếng đã từng nói, thưởng thức một món ngon không chỉ là ăn mà còn là cảm nhận, là hiểu về lịch sử, văn hóa của nơi đã sản sinh ra món ăn đó.\n" +
                "Hy vọng với bài viết này, chúng tôi đã giúp bạn có thêm một phần thông tin bổ ích về ẩm thực phương Tây: đơn giản nhưng cũng thật tinh tế và sang trọng.","12/01/2022"));
        addPost(new Post(11,"vuducmanh016@gmail.com","Samgyetang (Gà hầm sâm)","https://toplist.vn/images/800px/samgyetang-64525.jpg","https://www.youtube.com/embed/_bUwfPWnbBc","Món Ngon Châu Á","Đến với xứ sở kim chi, bạn nên thử qua món Samgyetang nổi tiếng, còn có nghĩa là gà tần sâm. Đây là một món ăn chứa nhiều chất đạm, chất bổ, giúp cơ thể cảm thấy khỏe khoắn, ngon miệng khi ăn. Hàn Quốc có đến ba mùa nóng, chính vì thế, bát canh Samgyetang lúc bấy giờ là một lựa chọn được yêu chuộng, giúp giải nhiệt cơ thể rất hiệu quả.\n" +
                "Thành phần của canh là gà non làm sạch, được nhồi sâm cùng gạo nếp, hoàng kỳ, táo tàu rồi khâu lại bằng chỉ, cho vào nồi đá hầm trong nhiều giờ. Chất ngọt từ thịt gà và vị thơm từ sâm hòa quyện vào nhau, tạo nên một bát Samgyetang nóng hổi, được nhiều người ưa chuộng. Tuy nhiên, có một lý do khiến nhiều du khách nước ngoài lấy làm khó hiểu là vì sao giữa lúc thời tiết nóng bức thì người Hàn Quốc lại chuộng ăn Samgyetang. Trong khi món gà hầm nhân sâm này lại cực kỳ nóng, nóng đến mức khi ăn thì ai cũng toát mồ hôi nhễ nhại.\n" +
                "Mặc dù Samgyetang là món ăn nóng như một nồi lẩu đang đun sôi sùng sục nhưng nhiều người Hàn vẫn tìm ăn món này vào những ngày nóng nhất năm là bởi lý do đặc biệt kể trên. Hiện nay, theo tập quán này thì không chỉ có người bản địa mà ngay cả những du khách nước ngoài đến Hàn Quốc đều tìm ăn món Samgyetang vào những ngày hè nóng nực để biết được cảm giác khi ăn mà mồ hôi chảy ròng ròng và cơ thể cảm thấy khỏe khoắn ra sao ngay sau khi thưởng thức xong món ăn.","15/02/2023"));
        addPost(new Post(12,"vuducmanh017@gmail.com","Lechón – Philippine","https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Lechon%2C_Taste_Buds%2C_San_Bruno_1.JPG/300px-Lechon%2C_Taste_Buds%2C_San_Bruno_1.JPG","https://www.youtube.com/embed/cQyoDpQuDpI","Món Ngon Châu Á","Lechon hay Lechón là một từ trong tiếng Tây Ban Nha có nghĩa là lợn sữa quay, được làm từ lợn sữa nguyên con, quay chín bằng than củi, nó còn được gọi là heo sữa quay theo kiểu Tây Ban Nha. Ngoài Philippines, món lợn quay lechón phổ biến ở nhiều nước như Dominica, Canada, Puerto Rico, Cuba, Tây Ban Nha và một số quốc gia nói tiếng Tây Ban Nha khác. Đặc sản lechón ở Philippines là món thịt lợn quay nguyên con phổ biến và được yêu thích tới mức dân địa phương còn tổ chức một lễ hội riêng cho món ăn này, và đây được xem như một nét văn hóa của Philippines.@Sau khi làm sạch lông, mổ bụng, con lợn sữa được để nguyên cả tai, đuôi, chân để ướp gia vị và quay trên than hồng trong 8 tiếng mới chín toàn bộ. Thời gian để chế biến lechón mất gần 8 tiếng. Quá trình chuẩn bị món này đặc biệt công phu. Lợn sữa phải được giết rồi mổ rộng bụng từ chân trước tới chân sau. Tay, đuôi và chân được làm sạch và để nguyên vẹn. Lông trên khắp thân lợ được cạo sạch. Đầu bếp phải kiểm tra cẩn thận để không còn lông trên con lợn trước khi ướp gia vị.\n" +
                "Khi đã được làm sạch, thịt lợn được ướp gia vị với tỏi giã dập, kinh giới khô, tiêu, muối và dầu điều. Lượng gia vị thường phụ thuộc vào kích cỡ của con lợn, dùng để ướp cả trong và ngoài con vật. Sau khâu này, con lợn được nhồi đầy bụng với các loại rồi để lạnh trong thùng lớn. Bước tiếp theo cũng quan trọng không kém khi quyết định độ chín của thịt chính là xiên vào que lớn và cố định con lợn để đặt lên bếp than hồng. Than dùng để nướng lợn không cần để lửa quá lớn.\n" +
                "Phương pháp nấu này làm cho da lợn giòn hơn và có hương vị đậm đà đặc biệt. Than hồng giúp món ăn có thêm mùi vị như “thịt xông khói” Khi quay xong, lợn được gỡ ra khỏi que xiên và để 20 – 30 phút trước khi cắt. Thời gian này để gia vị và mỡ của con lợn hòa quyện, ngập trong bụng lợn, làm thịt mềm, có độ ẩm vừa phải. Người nấu cũng phết thêm mỡ lên con lợn khiến lớp da bóng và hấp dẫn hơn. Món lechón Philippines sau khi đã nấu xong có lớp da giòn bóng hấp dẫn thực khách.\n" +
                "Trong khi lễ hội, những chú lợn sữa sẽ được lựa chọn vào nghi lễ này. Sau khi được chế biến sạch sẽ, bỏ hết nội tạng, những chú lợn sữa sẽ được nhồi gia vị như tương, ớt, lá me, dứa. Lớp da bên ngoài được tẩm ướp những gia vị bí truyền. Sau quy trình tẩm ướp, lợn được nướng trên hố than đỏ rực. Thời gian làm chín chú lợn kéo dài nhiều giờ. Lửa được duy trì cháy đều, nhỏ. Khi lớp da bên ngoài giòn tan như vầng cháy, vàng ruộm và thịt bên trong chín mềm, thì quy trình nướng lợn hoà\n" +
                "Riêng ở vùng đảo Cebu thuộc Philippines đây nơi được mệnh danh là vùng đất có món heo quay ngon với món heo sữa quay theo kiểu Tây Ban Nha (có tên chính gốc là lechón de leche). Món ăn có vị thanh, giòn, ngọt thịt từ bên trong. Heo được quay nguyên con với phần bụng được may kín trong quá trình quay. Tùy từng vùng miền, các loại thảo mộc và gia vị được cho vào bụng khác nhau, nhưng phổ biến nhất là muối và xả. Trong quá trình quay, các loại thảo mộc sẽ hòa tan và thấm vào phần thịt. Heo được quay kín trong thời gian 2 tiếng để đảm bảo toàn bộ phần thịt bên trong được chín đều. Lớp da lechón de leche giòn tan có thể cắt bằng đầu ngón tay.","16/03/2023"));
        addPost(new Post(13,"vuducmanh012@gmail.com","Rendang – Indonesia","https://media.2dep.vn/upload/hatran/2022/03/01/thit-bo-rendang-mon-ngon-thuong-xuat-hien-trong-cac-dip-le-ma-ban-nen-thu-khi-den-indonesia-1646104844-1.jpg","https://www.youtube.com/embed/oOQa8B1adO8","Món Ngon Châu Á","Khi nhắc đến ẩm thực Indonesia, du khách thường nhớ đến những món ăn như nasi gudeng (mít kho), nasi uduk (nước nấu cơm dừa), cá ướp cay, Rawon (súp thịt bò đen)… và Rendang. Redang có thể hiểu là món bò hầm mềm nhừ với hương vị phong phú, nhiều tầng, khi ăn có vị cay, béo, mềm đặc trưng. Bò Redang là một trong những món ăn biểu tượng của Indonesia và được không ít người lựa chọn là món ăn ngon nhất thế giới (đối với họ).\n" +
                "Thịt bò Rendang có nguồn gốc từ dân tộc Minangkabau của Indonesia. Trước đây, món ăn này thường chỉ được chế biến vào các dịp lễ tại đây, tuy nhiên hiện nay bạn có thể tìm kiếm món ăn này ở mọi thời điểm trong năm ở Indonesia.@Thoạt nhìn, món thịt bò Rendang có hình thức khá giống món cà-ri. Thế nhưng thực chất, món ăn này có cách làm và hương vị hoàn toàn khác biệt. Nguyên liệu để làm món thịt bò Rendang sẽ là thịt bò và các loại gia vị đặc trưng của Indonesia như gừng, hẹ tây, chanh, ớt, lá nghệ… Ngoài ra Rendang còn có cách chế biến độc đáo khiến nhiều người ấn tượng khi có dịp thưởng thức.\n" +
                "Cái tên “Rendang” của món ăn được bắt nguồn từ “merendang” với ý nghĩa món ăn được nấu trong chảo hoặc nồi với lửa liu riu. Khi chế biến món thịt bò Rendang, người đầu bếp phải khuấy liên tục và đều tay cho đến khi nước trong nồi bay hơi và cạn hết. Nghe có vẻ đơn giản nhưng người ta phải canh lửa và thời gian chuẩn để thịt dù đã chín mềm nhưng không bị nát và thấm đẫm gia vị.@Rendang được chia làm hai loại là khô và ướt. Với món thịt bò Rendang khô, người đầu bếp sau khi chế biến xong sẽ mang đi sấy khô và bảo quản từ 3 đến 4 tháng, món Rendang khô thường được người Indonesia chuẩn bị để dùng cho cách dịp lễ hội. Còn món thịt bò Rendang ướt (còn được gọi là Kalio) sẽ có thời gian sử dụng ít hơn, chỉ khoảng 1 tháng sau khi chế biến.@Bên cạnh nguyên liệu thịt bò thì người Indonesia cũng dùng cách nấu Rendang với nhiều nguyên liệu khác như thịt lợn, thịt gà, thịt vịt. Khi sử dụng thịt bò, người ta sẽ dùng nước dừa và nấu đến khi thịt chín mềm. Còn khi sử dụng thịt gà, thịt vịt, món Rendang sẽ được sử dụng thêm me và không cần phải nấu kĩ như khi làm với nguyên liệu thịt bò.\n" +
                "Món thịt bò Rendang thường được người Indonesia sử dụng khi ăn cùng cơm trắng, cơm nếp hoặc cơm nắm. Ngoài ra thì tuỳ vào sở thích và khẩu vị, nhiều người cũng ăn món thịt bò này cùng các món ăn kèm như cà-ri mít non, lá khoai mì luộc, nước sốt ớt xanh hoặc đỏ, cà ri bắp cải… để món ăn thêm tròn vị và hấp dẫn hơn.\n" +
                "Nếu có dịp đến Indonesia, đặc biệt là đảo Bali, bạn đừng quên thưởng thức món thịt bò Rendang đặc biệt này. Bên cạnh đó, bạn cũng có thể tìm mua loại thịt bò Rendang khô để mang về làm quà tặng gia đình, bạn bè.",date));
    }


    public long addCommentItem(Comment i) {
        ContentValues values = new ContentValues();
        values.put("id",i.getId());
        values.put("username", i.getUsername());
        values.put("id_post", i.getId_post());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("comments", null, values);
    }

    public long addComment(Comment i) {
        ContentValues values = new ContentValues();
        values.put("username", i.getUsername());
        values.put("id_post", i.getId_post());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("comments", null, values);
    }
    public int deleteAllComments(){
        SQLiteDatabase sqLiteHelper=getWritableDatabase();
        return sqLiteHelper.delete("comments",null,null);
    }
    public void initDataComment() {
        deleteAllComments();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        addCommentItem(new Comment(1,"vuducmanh015@gmail.com","Nhìn hấp dẫn quá",date ,1));
        addCommentItem(new Comment(2,"vuducmanh016@gmail.com","Tôi sẽ bắt tay ngay vào món ăn này",date ,1));
        addCommentItem(new Comment(3,"vuducmanh017@gmail.com","Bài viết hay",date ,1));
        addCommentItem(new Comment(4,"vuducmanh012@gmail.com","Tôi muốn thử",date ,2));
        addCommentItem(new Comment(5,"vuducmanh013@gmail.com","Thông tin thật bổ ích",date ,2));
        addCommentItem(new Comment(6,"vuducmanh014@gmail.com","Đáng thử",date ,3));
        addCommentItem(new Comment(7,"vuducmanh016@gmail.com","Thật tuyệt vời",date ,4));
        addCommentItem(new Comment(8,"vuducmanh017@gmail.com","Hãy cho tôi địa chỉ nơi bán món ăn này",date ,5));
    }
    public List<Comment> getCommnetByIdPost(int id_post) {
        List<Comment> list = new ArrayList<>();
        String whereClause = "id_post = "+id_post;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "id DESC";
        Cursor rs = sqLiteDatabase.query("comments",
                null, whereClause, null,
                null, null, order);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String username = rs.getString(1);
            String id_p = rs.getString(2);
            String content = rs.getString(3);
            String date = rs.getString(4);
            System.out.println(content);
            list.add(new Comment(id,username,content,date,Integer.parseInt(id_p)));
        }
        return list;
    }

    public long addView(Views i) {
        ContentValues values = new ContentValues();
        values.put("username", i.getUsername());
        values.put("id_post", i.getId_post());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("views", null, values);
    }
    public long addViewItem(Views i) {
        ContentValues values = new ContentValues();
        values.put("id",i.getId());
        values.put("username", i.getUsername());
        values.put("id_post", i.getId_post());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("views", null, values);
    }
    public int deleteAllViews(){
        SQLiteDatabase sqLiteHelper=getWritableDatabase();
        return sqLiteHelper.delete("views",null,null);
    }

    public void initDataViews() {
        deleteAllViews();
        addViewItem(new Views(1,"vuducmanh012@gmail.com",1));
        addViewItem(new Views(2,"vuducmanh012@gmail.com",2));
        addViewItem(new Views(3,"vuducmanh012@gmail.com",3));
        addViewItem(new Views(4,"vuducmanh012@gmail.com",4));
        addViewItem(new Views(5,"vuducmanh013@gmail.com",5));
        addViewItem(new Views(6,"vuducmanh013@gmail.com",6));
        addViewItem(new Views(7,"vuducmanh013@gmail.com",7));
        addViewItem(new Views(8,"vuducmanh014@gmail.com",8));
        addViewItem(new Views(9,"vuducmanh014@gmail.com",9 ));
        addViewItem(new Views(10,"vuducmanh015@gmail.com",10 ));
        addViewItem(new Views(11,"vuducmanh015@gmail.com",11 ));
        addViewItem(new Views(12,"vuducmanh015@gmail.com",12 ));
        addViewItem(new Views(13,"vuducmanh016@gmail.com",13 ));
        addViewItem(new Views(14,"vuducmanh016@gmail.com",1 ));
        addViewItem(new Views(15,"vuducmanh016@gmail.com",2 ));
        addViewItem(new Views(16,"vuducmanh013@gmail.com",1));
        addViewItem(new Views(17,"vuducmanh013@gmail.com",2));
        addViewItem(new Views(18,"vuducmanh013@gmail.com",3));
        addViewItem(new Views(19,"vuducmanh017@gmail.com",7));
        addViewItem(new Views(20,"vuducmanh017@gmail.com",8));
        addViewItem(new Views(21,"vuducmanh017@gmail.com",1));
        addViewItem(new Views(22,"vuducmanh017@gmail.com",4));

    }

    public int getViewByIdPost(int id_post) {
        int count = 0;
        String whereClause = "id_post = "+id_post;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("views",
                null, whereClause, null,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            count+=1;
        }
        return count;
    }

    public long addRatingItem(Rating i) {
        ContentValues values = new ContentValues();
        values.put("id",i.getId());
        values.put("username", i.getUsername());
        values.put("id_post", i.getId_post());
        values.put("rating", i.getRating());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("ratings", null, values);
    }
    public long addRating(Rating i) {
        ContentValues values = new ContentValues();
        values.put("username", i.getUsername());
        values.put("id_post", i.getId_post());
        values.put("rating", i.getRating());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("ratings", null, values);
    }
    public int updateRatings(Rating i){
        ContentValues values = new ContentValues();
        values.put("username", i.getUsername());
        values.put("id_post", i.getId_post());
        values.put("rating", i.getRating());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("ratings", values,whereClause,whereArgs);
    }

    public Rating findRatings(String username, int id_post){

        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereClause = "id_post = ? AND username = ?";
        String[] whereArgs = {Integer.toString(id_post),username};
        Cursor rs = sqLiteDatabase.query("ratings",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String uname = rs.getString(1);
            int id_p = rs.getInt(2);
            float rating = Float.parseFloat(rs.getString(3));
            Rating rt = new Rating(id,uname,id_p,rating);
            return rt;
        }
        return null;
    }

    public int deleteAllRatings(){
        SQLiteDatabase sqLiteHelper=getWritableDatabase();
        return sqLiteHelper.delete("ratings",null,null);
    }
    public void initDataRating() {
        deleteAllRatings();
        addRatingItem(new Rating(1,"vuducmanh012@gmail.com",1 ,4.5f));
        addRatingItem(new Rating(2,"vuducmanh013@gmail.com",2 ,4f));
        addRatingItem(new Rating(3,"vuducmanh014@gmail.com",3 ,3.5f));
        addRatingItem(new Rating(4,"vuducmanh015@gmail.com",4 ,2f));
        addRatingItem(new Rating(5,"vuducmanh016@gmail.com",5 ,4.5f));
        addRatingItem(new Rating(6,"vuducmanh017@gmail.com",6 ,3.5f));
        addRatingItem(new Rating(7,"vuducmanh017@gmail.com",1 ,3.5f));
        addRatingItem(new Rating(8,"vuducmanh016@gmail.com",2 ,2f));
        addRatingItem(new Rating(9,"vuducmanh015@gmail.com",3 ,1.5f));
        addRatingItem(new Rating(10,"vuducmanh014@gmail.com",4 ,4f));
        addRatingItem(new Rating(11,"vuducmanh013@gmail.com",5 ,3f));
        addRatingItem(new Rating(12,"vuducmanh012@gmail.com",4 ,4.5f));

    }
    public float getRatingByIdPost(int id_post) {
        float rt = 0f;
        int count = 0;
        String whereClause = "id_post = "+id_post;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("ratings",
                null, whereClause, null,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            count+=1;
            rt += Float.parseFloat(rs.getString(3));
        }
        if(count==0){
            return rt;
        }
        return rt/count;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    //get all Post include order by descending
    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = sqLiteDatabase.query("posts",
                null, null, null,
                null, null, order);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String username = rs.getString(1);
            String title = rs.getString(2);
            String image = rs.getString(3);
            String video = rs.getString(4);
            String category = rs.getString(5);
            String content = rs.getString(6);
            String date = rs.getString(7);
            list.add(new Post(id,username, title, image,video, category, content, date));
        }
        return list;
    }
    public RViewPostItem getRViewPostItem(int id_post) {
        RViewPostItem rpost = new RViewPostItem();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id_post)};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("posts",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String username = rs.getString(1);
            String title = rs.getString(2);
            String image = rs.getString(3);
            String video = rs.getString(4);
            String category = rs.getString(5);
            String content = rs.getString(6);
            String date = rs.getString(7);
            System.out.println(date);
            float rt = getRatingByIdPost(id);
            System.out.println("id_post: "+id+" rt:"+rt);
            int luotxem = getViewByIdPost(id);
            rpost = new RViewPostItem(new Post(id,username, title, image,video, category, content, date),rt,luotxem);
        }
        return rpost;
    }

    public List<RViewPostItem> getRViewPostsByUser(String email) {
        List<RViewPostItem> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "date DESC";
        String whereClause = "username = ?";
        String[] whereArgs = {email};
        Cursor rs = sqLiteDatabase.query("posts",
                null, whereClause, whereArgs,
                null, null, order);
        while ((rs != null) && (rs.moveToNext())) {

            int id = rs.getInt(0);
            System.out.println("id "+id);
            String username = rs.getString(1);
            String title = rs.getString(2);
            String image = rs.getString(3);
            String video = rs.getString(4);
            String category = rs.getString(5);
            String content = rs.getString(6);
            String date = rs.getString(7);
            System.out.println(date);
            float rt = getRatingByIdPost(id);
            System.out.println("id_post: "+id+" rt:"+rt);
            int luotxem = getViewByIdPost(id);
            list.add(new RViewPostItem(new Post(id,username, title, image,video, category, content, date),rt,luotxem));

        }
        return list;
    }

    public List<RViewPostItem> getRViewPostsByUserRatingHistory(String email) {
        List<RViewPostItem> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereClause = "username = ?";
        String[] whereArgs = {email};
        Cursor rs = sqLiteDatabase.query("ratings",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id_post = rs.getInt(2);
            list.add(getRViewPostItem(id_post));
        }
        return list;
    }
    public List<RViewPostItem> getRViewPostsByUserViewHistory(String email) {
        List<RViewPostItem> list = new ArrayList<>();
        Set<Integer> setPostId = new HashSet<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereClause = "username = ?";
        String[] whereArgs = {email};
        Cursor rs = sqLiteDatabase.query("views",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id_post = rs.getInt(2);
            setPostId.add(id_post);
        }
        for (int id_p: setPostId){
            list.add(getRViewPostItem(id_p));
        }
        return list;
    }


    public List<RViewPostItem> getAllRViewPosts() {
        System.out.println("bat dau dbug");
        List<RViewPostItem> list = new ArrayList<>();
        System.out.println("dbug 0");
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "date DESC";
        System.out.println("dbug 1");
        Cursor rs = sqLiteDatabase.query("posts",
                null, null, null,
                null, null, order);
        System.out.println("dbug 2");
        while ((rs != null) && (rs.moveToNext())) {

            int id = rs.getInt(0);
            System.out.println("id "+id);
            String username = rs.getString(1);
            String title = rs.getString(2);
            String image = rs.getString(3);
            String video = rs.getString(4);
            String category = rs.getString(5);
            String content = rs.getString(6);
            String date = rs.getString(7);
            System.out.println(date);
            float rt = getRatingByIdPost(id);
            System.out.println("id_post: "+id+" rt:"+rt);
            int luotxem = getViewByIdPost(id);
            list.add(new RViewPostItem(new Post(id,username, title, image,video, category, content, date),rt,luotxem));

        }
        return list;
    }
//    //get all Comment by post_id order by descending
//    public List<Comment> searchByCategory(int id_post) {
//        List<Comment> list = new ArrayList<>();
//        String whereClause = "id_post = ?";
//        String order = "date DESC";
//        String[] whereArgs = {String.valueOf(id_post)};
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, order);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id = rs.getInt(0);
//            String username = rs.getString(1);
//            int id_p = rs.getInt(2);
//            String content = rs.getString(3);
//            String date = rs.getString(4);
//            list.add(new Comment(id,username, content, date, id_p));
//        }
//        return list;
//    }

    //get all Comment by post_id order by descending
    public List<RViewPostItem> searchByCategoryandTitle(String searchInfo) {
        List<RViewPostItem> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        String order = "date DESC";
        String whereClause = "category like '%"+searchInfo+"%' OR title like '%"+searchInfo+"%'";
        Cursor rs = sqLiteDatabase.query("posts",
                null, whereClause, null,
                null, null, null);
        System.out.println("dbug 2");
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            System.out.println("id "+id);
            String username = rs.getString(1);
            String title = rs.getString(2);
            String image = rs.getString(3);
            String video = rs.getString(4);
            String category = rs.getString(5);
            String content = rs.getString(6);
            String date = rs.getString(7);
            System.out.println(date);
            float rt = getRatingByIdPost(id);
            System.out.println("id_post: "+id+" rt:"+rt);
            int luotxem = getViewByIdPost(id);
            list.add(new RViewPostItem(new Post(id,username, title, image,video, category, content, date),rt,luotxem));
        }
        return list;
    }

//    //lay cac item theo date
//    public List<Item> getByDate(String date) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date like ?";
//        String[] whereArgs = {date};
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            list.add(new Item(id, title, category, price, date));
//        }
//        return list;
//    }
//    //update
//    public int update(Item i){
//        ContentValues values = new ContentValues();
//        values.put("title", i.getTitle());
//        values.put("category", i.getCategory());
//        values.put("price", i.getPrice());
//        values.put("date", i.getDate());
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        String whereClause = "id= ?";
//        String[] whereArgs = {Integer.toString(i.getId())};
//        return sqLiteDatabase.update("items", values,whereClause,whereArgs);
//    }
//    public int delete(int id){
//        String whereClause = "id= ?";
//        String[] whereArgs = {Integer.toString(id)};
//        SQLiteDatabase sqLiteHelper=getWritableDatabase();
//        return sqLiteHelper.delete("items",whereClause,whereArgs);
//    }
//    public List<Item> searchByTitle(String key) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "title like ?";
//        String[] whereArgs = {"%" + key +"%"};
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            list.add(new Item(id, title, category, price, date));
//        }
//        return list;
//    }
//    public List<Item> searchByCategory(String category) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "category like ?";
//        String[] whereArgs = {category};
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String c = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            list.add(new Item(id, title, c, price, date));
//        }
//        return list;
//    }
//    public List<Item> searchByDateFromTo(String from, String to) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date BETWEEN date(?) AND date(?) ";
//        String[] whereArgs = {from.trim(),to.trim()};
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            list.add(new Item(id, title, category, price, date));
//        }
//        return list;
//    }
}
