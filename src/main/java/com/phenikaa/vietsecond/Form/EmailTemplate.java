package com.phenikaa.vietsecond.Form;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {
    public String verificationAccount(String fullName,String token){
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "            font-family: \"Helvetica Neue\", Helvetica;\n" +
                "        }\n" +
                "\n" +
                "        html,\n" +
                "        body {\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            background-color: rgb(222, 244, 225);\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            margin: 2%;\n" +
                "            width: 600px;\n" +
                "            height: 600px;\n" +
                "            background-color: white\n" +
                "        }\n" +
                "\n" +
                "        .header,\n" +
                "        .footer {\n" +
                "            width: 100%;\n" +
                "            background-color: #7ED958;\n" +
                "            height: 100px;\n" +
                "           text-align: center;"+
                "            display:block;\n" +
                "        }\n" +
                "\n" +
                "        .body {\n" +
                "            height: 400px;\n" +
                "        }\n" +
                "\n" +
                "        .header>img {\n" +
                "            height: 100%;\n" +
                "            display:inline-block;\n" +
                "        }\n" +
                "\n" +
                "        .header {\n" +
                "            color: white;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .header>h1 {\n" +
                "        position:absolute;\n" +
                "        top: 35px;\n" +
                "        right: 155px;\n" +
                "        }\n" +
                "\n" +
                "        .tex {\n" +
                "            margin: 15px 30px;\n" +
                "          \n" +
                "        }\n" +
                "        .tex>p{\n" +
                "            font-weight: 100;\n" +
                "        }\n" +
                "        .vsc {\n" +
                "            color: #49ac1f;\n" +
                "        }\n" +
                "\n" +
                "        .confirm {\n" +
                "            width: 100%;\n" +
                "            padding:10px 217px;\n" +
                "            margin:30px 0;\n" +
                "      \n" +
                "        }\n" +
                "\n" +
                "        .confirm>a {\n" +
                "            border-radius: 5px;\n" +
                "            background-color: #7ED958;\n" +
                "            padding: 15px 40px;\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            color: white;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        .thanks{\n" +
                "            margin: 15px 30px;\n" +
                "            float: left;\n" +
                "            font-size:14px;\n" +
                "        }\n" +
                "        .footer{\n" +
                "            padding-top: 45px;\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            font-weight: 100;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <img src=\"https://cdn.discordapp.com/attachments/1009450253537247274/1031370541375955034/vietsecond.jpg\">\n" +

                "        </div>\n" +
                "        <div class=\"body\">\n" +
                "            <div class=\"current\">\n" +
                "                <div class=\"tex\"><p>Xin ch??o <b>"+fullName+"</b></p></div>\n" +
                "                <div class=\"tex\"><p>C???m ??n b???n ???? ????ng k?? t??i kho???n c???a <b class=\"vsc\">Vietsecond</b></p> </div>\n" +
                "                <div class=\"tex\"><p>????? ti???p t???c s??? d???ng t??i kho???n n??y ????? ????ng nh???p v??o\n" +
                "                    <b class=\"vsc\">Vietsecond</b> b???n vui l??ng nh???n v??o n??t b??n d?????i ????? x??c th???c t??i kho???n</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"confirm\">\n" +
                "                <a href=\"http://localhost:3000/verification/"+token+"\">X??c th???c</a>\n" +
                "            </div>\n" +
                "            <div class=\"tex\">\n" +
                "               <p>N???u b???n kh??ng ph???i l?? ng?????i g???i y??u c???u x??c th???c, vui l??ng b??? qua email n??y.</p> \n" +
                "            </div>\n" +
                "            <div class=\"thanks\">\n" +
                "                <p class=\"ths\"><i>Tr??n tr???ng,</i></p>\n" +
                "                <p class=\"ths\"><b class=\"vsc\"><i>Vietsecond</i></b</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            Copyright ?? 2022 Vietsecond\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return content;
    }

    public String resetPassword(String fullName,String token){
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "            font-family: \"Helvetica Neue\", Helvetica;\n" +
                "        }\n" +
                "\n" +
                "        html,\n" +
                "        body {\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            background-color: rgb(222, 244, 225);\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "\n" +
                "        .container {\n" +
                "            margin: 2%;\n" +
                "            width: 600px;\n" +
                "            height: 600px;\n" +
                "            background-color: white\n" +
                "        }\n" +
                "\n" +
                "        .header,\n" +
                "        .footer {\n" +
                "            width: 100%;\n" +
                "            background-color: #7ED958;\n" +
                "            height: 100px;\n" +
                "            display:block;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .body {\n" +
                "            height: 400px;\n" +
                "        }\n" +
                "\n" +
                "        .header>img {\n" +
                "            height: 100%;\n" +
                "            display:inline-block;\n" +
                "        }\n" +
                "\n" +
                "        .header {\n" +
                "            color: white;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .header>h1 {\n" +
                "        position:absolute;\n" +
                "        top: 35px;\n" +
                "        right: 155px;\n" +
                "        }\n" +
                "\n" +
                "        .tex {\n" +
                "            margin: 15px 30px;\n" +
                "          \n" +
                "        }\n" +
                "        .tex>p{\n" +
                "            font-weight: 100;\n" +
                "        }\n" +
                "        .vsc {\n" +
                "            color: #49ac1f;\n" +
                "        }\n" +
                "\n" +
                "        .confirm {\n" +
                "            width: 100%;\n" +
                "            padding:10px 190px;\n" +
                "            margin:30px 0;\n" +
                "      \n" +
                "        }\n" +
                "\n" +
                "        .confirm>a {\n" +
                "            border-radius: 5px;\n" +
                "            background-color: #7ED958;\n" +
                "            padding: 15px 40px;\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            color: white;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "        .thanks{\n" +
                "            margin: 15px 30px;\n" +
                "            float: left;\n" +
                "            font-size:14px;\n" +
                "        }\n" +
                "        .footer{\n" +
                "            padding-top: 45px;\n" +
                "            text-align: center;\n" +
                "            color: white;\n" +
                "            font-weight: 100;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <img src=\"https://cdn.discordapp.com/attachments/1009450253537247274/1031370541375955034/vietsecond.jpg\">\n" +
                "\n" +
                "        </div>\n" +
                "        <div class=\"body\">\n" +
                "            <div class=\"current\">\n" +
                "                <div class=\"tex\"><p>Xin ch??o <b>"+fullName+"</b></p></div>\n" +
                "                <div class=\"tex\"><p>Ch??ng t??i v???a nh???n ???????c y??u c???u ?????t l???i m???t kh???u c???a b???n </div>\n" +
                "                <div class=\"tex\"><p>????? ?????t l???i m???t kh???u m???i cho t??i kho???n c???a b???n vui l??ng nh???n v??o n??t b??n d?????i ????? chuy???n sang trang ?????i m???t kh???u !</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"confirm\">\n" +
                "                <a href=\"http://localhost:3000/reset-password/"+token+"\">?????i m???t kh???u</a>\n" +
                "            </div>\n" +
                "            <div class=\"tex\">\n" +
                "               <p>N???u b???n kh??ng ph???i l?? ng?????i g???i y??u c???u x??c th???c, vui l??ng b??? qua email n??y.</p> \n" +
                "            </div>\n" +
                "            <div class=\"thanks\">\n" +
                "                <p class=\"ths\"><i>Tr??n tr???ng,</i></p>\n" +
                "                <p class=\"ths\"><b class=\"vsc\"><i>Vietsecond</i></b</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            Copyright ?? 2022 Vietsecond\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return content;
    }

}
