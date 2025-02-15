<%@page import="util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%
    // リクエストパラメータから取得
    String param1 = request.getParameter("num");
    int stonesTaken = 0;

    // isNullOrEmpty メソッドを使って null または空文字をチェック
    if (!Utility.isNullOrEmpty(param1) && param1.matches("\\d+")) { 
        stonesTaken = Integer.parseInt(param1);
    }

    // 残りの石の計算
    // 初回アクセス時にセッション変数を初期化
    if (session.getAttribute("totalNum") == null) {
        session.setAttribute("totalNum", 25); // 初期値をセット
    }

    // セッションから int 型の値を取得
    int totalNum = (int) session.getAttribute("totalNum"); // キャストを使用

    // 石を減らす
    totalNum -= stonesTaken;

    // セッションに保存
    session.setAttribute("totalNum", totalNum);

    // 石が0以下なら結果ページへリダイレクト
    if (totalNum <= 0) {
        response.sendRedirect("finish.jsp");
        return; // リダイレクト後の処理を防ぐ
    }

    // プレイヤーの切り替え
    String player = (String) session.getAttribute("player");
    if (Utility.isNullOrEmpty(player)) { // isNullOrEmpty を使用
        player = "B"; // 初期値
    }

    if (player.equals("B")) {
        player = "A";
    } else {
        player = "B";
    }
    session.setAttribute("player", player);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>石取りゲーム</title>
<link href="css/styles.css" rel="stylesheet">
</head>
<body>
  <h1>石取りゲーム</h1>

  <div class="info">
    <h2>残りの石: <%= totalNum %></h2>
    <p class="stone"><%= Utility.getStoneDisplayHtml(totalNum) %></p>
  </div>
  <div class="info">
    <h2>プレイヤー<%= player %>の番</h2>
    <form action="play.jsp" method="GET">
      <p>
        石を
        <input type="number" name="num" min="1" max="3" required>
        個取る<br> ※1度に最大3個まで取れます。
      </p>
      <button class="btn" type="submit">決定</button>
    </form>
  </div>
</body>
</html>