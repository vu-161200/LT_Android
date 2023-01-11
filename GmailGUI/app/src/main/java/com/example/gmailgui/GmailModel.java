package com.example.gmailgui;

import android.graphics.Color;

import java.util.Arrays;
import java.util.List;

public class GmailModel {
    private String email;
    private String title;
    private String content;
    private String time;
    private boolean isFavourite;
    private String color;

    public GmailModel(String email, String title, String content, String time, boolean isFavourite, String color) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isFavourite = isFavourite;
        this.color = color;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getParseColor(){
        return Color.parseColor(this.color);
    }

    static List<GmailModel> gmails = Arrays.asList(
            new GmailModel("Navicat Support",
                    "[Navicat] Your request (96692) has been received",
                    "##- Please type your reply above this line -##\nYour request (96692) has been received and is being reviewed by our support staff.\nTo add additional comments, reply to this email.\nThis email is a service from Navicat. Delivered by Zendesk",
                    "12/11/2022",
                    false,
                    "#FFA500"
            ),
            new GmailModel("LeetCode",
                    "\uD83D\uDD14 Discount Extended for 1 Day - $30 off Annual Premium Subscription!",
                    "For LeetCoders who missed our black Friday deal, this is your chance! The $30 off on our annual premium subscription is extended for one day!\nRemember to enter the code \"THANKS2022\" at checkout.\nPlus, get 20% off on our highly rated LeetCode's Interview Crash Course: Data Structures and Algorithms! Get the $89.99 course at just $69.99. The discount is already applied. No coupon code is needed!\nIf you have LeetCode Annual Premium, you'll save an extra $35 off the discounted price when purchasing the course.\nTo take advantage of the extended offers, subscribe before 11:59pm PST on Tuesday, November 29.",
                    "29/11/2022",
                    false,
                    "#FFD700"
            ),
            new GmailModel("NVIDIA Accounts",
                    "Authenticate Your Email Address",
                    "Hello,\nYour account just logged in using a(n) Windows device we don't recognize from a region we haven't seen you in recently. Click the link below to verify this was you and continue to NVIDIA GeForce Experience. If this wasn't you please change your GOOGLE password.\n\nLocation: Bac Ninh\n\nTo protect your privacy we do not store the specific location. Southern Asia is the location that will be retained for future reference.\n",
                    "12/12/2022",
                    false,
                    "#EE82EE"
            ),
            new GmailModel("Dribbble",
                    "New Course! 📣 8-Week Design Career Prep with Helen Tran",
                    "Land your next design job, guaranteed.\n8-WEEK PRODUCT DESIGN CAREER PREP\nAre you ready to make your next career move? Land your next best design job in Dribbble's new Product Design Career Preparation Course led by acclaimed Designer, Founder, and Educator, Helen Tran.\n'At the beginning of my career, I spent five years trying to break into product design. No one should have to waste that much time.'",
                    "20/12/2022",
                    false,
                    "#32CD32"
            ),
            new GmailModel("Oded Valin",
                    "One question",
                    "Hi Tiiee,\n\nI wanted to personally follow up, did you get your SQL queries optimized?\nI'm here to help, and I usually reply within 10 minutes.\n\nOded Valin\nCo-founder & CEO, EverSQL",
                    "24/12/2022",
                    false,
                    "#00FFFF"
            ),
            new GmailModel("OneDrive",
                    "Many files were recently deleted from your OneDrive",
                    "Files are permanently removed from your OneDrive recycle bin 30 days after they were deleted\nHi Tiiee,\nWe noticed that you recently deleted a large number of files from your OneDrive.\nWhen files are deleted, they are moved to your OneDrive recycle bin. If you don't restore deleted files from the OneDrive recycle bin, they are permanently deleted after 30 days.\nIf you want to restore these files, go to the OneDrive recycle bin, select what you want to restore, and click the Restore button.\nYou can ignore this mail if you meant to get rid of these files.",
                    "4/1/2023",
                    false,
                    "#7B68EE"
            ),
            new GmailModel("Apple",
                    "ID Apple của bạn đã được dùng để đăng nhập vào iCloud trên một trình duyệt web",
                    "Xin chào Tiiee •,\nID Apple của bạn (x@gmail.com) đã được dùng để đăng nhập vào iCloud trên một trình duyệt web.\nNgày và giờ: 3/1/2023 vào lúc 18:03 UTC\nHệ Điều Hành:Windows\nNếu bạn đã biết các thông tin trên, bạn có thể bỏ qua thư này.\nNếu gần đây bạn không đăng nhập vào iCloud và cho rằng có người đã truy cập vào tài khoản của bạn, hãy vào ID Apple (https://appleid.apple.com) để đổi mật khẩu càng sớm càng tốt.\nTrân trọng,\nHỗ Trợ Apple",
                    "5/1/2023",
                    false,
                    "#FFA500"
            ),
            new GmailModel("Grab",
                    "THÔNG BÁO TỪ GRAB VỀ PHỤ PHÍ TẾT NGUYÊN ĐÁN 2023",
                    "Quý Người dùng thân mến,\nNhằm duy trì chất lượng dịch vụ và đáp ứng nhu cầu tăng cao trong dịp Tết Nguyên đán Quý Mão 2023, Grab xin thông báo áp dụng “Phụ phí Tết Nguyên đán” áp dụng từ ngày 20/01/2023 - 26/01/2023 đối với các chuyến xe/ đơn hàng phát sinh trong khung giờ từ 6:00 giờ đến 22:00 giờ\nNhân dịp Tết Nguyên đán, Grab mong bạn và gia đình sẽ có những hành trình vui vẻ, ấm áp và an toàn cùng Grab. Cảm ơn bạn đã luôn ủng hộ Grab trong thời gian vừa qua, chúc bạn và gia đình một năm mới vạn sự như ý, sức khoẻ dồi dào.",
                    "6/1/2023",
                    false,
                    "#EE82EE"
            ),
            new GmailModel("Bartleby Learn",
                    "Scoring A's is hard. Luckily, this part of the process is not.",
                    "Hey Tiiee!\nYou're just a few numbers away from starting your journey towards better grades.\nBUT,\nwe're not talking numbers like points on an exam.\nThese numbers are way easier to obtain (perhaps they're in your wallet) — you only need to finish entering in your credit card details in order to enter the bartleby e-library. Then you'll be ready to access 1 million+ textbook solutions to help boost those grades! Plus, our subject matter experts are waiting 24/7 to answer your homework questions with Q&A.",
                    "7/1/2023",
                    false,
                    "#00FFFF"
            ),
            new GmailModel("Zuzanna from Studocu",
                    "Tiiee, chia sẻ góp ý của mình và nhận được 20 USD!",
                    "Xin chào Tiiee,\nChúng tôi đang nỗ lực từng ngày để xây dựng nền tảng Studocu ngày một hữu dụng hơn và rất mong được lắng nghe phản hồi của người dùng về hướng để cải tiến nền tảng này nhiều hơn nữa. Chúng tôi rất cảm kích khi bạn có thể dành ra 5 phút để hoàn thành khảo sát nhỏ do chúng tôi xây dựng này. \uD83D\uDE4F\nBạn sẽ có cơ hội nhận được phần thưởng là 20 USD khi hoàn thành khảo sát - Chúng tôi sẽ trao thưởng ngẫu nhiên cho 20 người tham gia \uD83D\uDCB8\nXin cảm ơn bạn rất nhiều và xin bảy tỏ sự cảm kích vì đã dành thời gian cho chúng tôi!\nThân mến,",
                    "8/1/2023",
                    false,
                    "#0000FF"
            ),
            new GmailModel("Riot Games",
                    "Login Code: 487907",
                    "Login Code\n\nHere is your login approval code:\n4 8 7 9 0 7\nIf this request did not come from you, change your account password immediately to prevent further unauthorized access. Read Protecting Your Account for tips on password strength.",
                    "9/1/2023",
                    false,
                    "#FFA500"
            ),
            new GmailModel("Salesforce",
                    "Heroku MFA Enforcement Is Coming Soon: Don’t Get Locked Out",
                    "Security Notification\nSalesforce Will Start Enforcing Multi-Factor Authentication for Heroku In About 30 Days\nNow that the MFA requirement is in effect, the date when multi-factor authentication becomes a permanent part of the Heroku login experience is quickly approaching. Beginning January 10, 2023, Salesforce will enforce MFA for all users who log in directly to the Heroku Dashboard or Heroku CLI.\nWe noticed you haven’t enabled MFA yet. Here’s what to expect when Salesforce starts enforcing MFA:\n- Starting January 10, 2023, users will receive an MFA challenge each time they log in. If a user hasn’t already registered for MFA, they’ll be prompted to do so before they can get access to the Heroku Dashboard and CLI.\n- Enterprise Admins won’t be able to turn off MFA enforcement for their organization.",
                    "08:30 AM",
                    false,
                    "#7B68EE"
            ),
            new GmailModel("Overleaf",
                    "Welcome to Overleaf",
                    "Welcome to Overleaf\nHi,\nThanks for signing up to Overleaf! If you ever get lost, you can log in again with the email address 'einestimono2@gmail.com'.\nIf you're new to LaTeX, take a look at our Help Guides and Templates.\nPS. We love talking to our users about Overleaf. Reply to this email to get in touch with us directly, whatever the reason. Questions, comments, problems, suggestions, all welcome!",
                    "11:04 PM",
                    false,
                    "#9ACD32"
            )
    );
}