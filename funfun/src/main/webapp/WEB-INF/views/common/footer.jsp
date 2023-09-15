<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
</head>
<body>
<footer class="light-footer">
    <div class="widget-area">
        <div class="container">
            <div class="row">

                <div class="col-sm-6 col-md-4 widget">
                    <div class="about-widget">
                        <div class="widget-title-image">
                            <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="">
                        </div>
                        <p><span style="color: #AAADBF; font-size: 15px;">국내에서 개최되는 국제 영화제를 대상으로 하는 크라우드 펀딩 사이트</span><br><br>영화제의 성공적인 개최를 위한 후원 펀딩을 모집하여 후원금을 영화제에 제공하고
								영화제에서 일정금액 이상 후원하는 사람들에게 영화제 티켓, 영화제 굿즈 등 소정의 보상을 줄 수 있도록 
								영화제와 후원자를 연결하는 플랫폼 사이트 입니다.</p>
                    </div><!-- / about-widget -->
                </div><!-- / widget -->
                <!-- / first widget -->
                <div class="col-sm-6 col-md-2 widget">
                    <div class="widget-title">
                        <h4>Brands</h4>
                    </div>
                    <div class="link-widget">
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/donation/shopfull?select=all">전체 영화제</a>
                        </div>
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/donation/shopfull?select=ongoing">진행 중 영화제</a>
                        </div>
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/donation/shopfull?select=upcoming">진행 예정 영화제</a>
                        </div>
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/donation/shopfull?select=sponsor">후원자 순</a>
                        </div>
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/donation/shopfull?select=collected">금액 순 영화제</a>
                        </div>
                    </div>
                </div><!-- / widget -->
                <!-- / second widget -->
                <div class="col-sm-6 col-md-2 widget">
                    <div class="widget-title">
                        <h4>커뮤니티</h4>
                    </div>
                    <div class="link-widget">
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/community/notice">공지사항</a>
                        </div>
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/community/qna_list">Q&A 게시판</a>
                        </div>
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/community/faq">FAQ</a>
                        </div>
                    </div>
                </div><!-- / widget -->
                <!-- / third widget -->

                <div class="col-sm-6 col-md-4 widget">
                    <div class="widget-title">
                        <h4>Get in Touch</h4>
                    </div>
                    <div class="contact-widget">
                        <div class="info">
                            <p><i class="lnr lnr-map-marker"></i><span>서울특별시 강남구 역삼동 823 4층</span></p>
                        </div>
                        <div class="info">
                            <a href="tel:+0123456789"><i class="lnr lnr-phone-handset"></i><span>+0123 000 000</span></a>
                        </div>
                        <div class="info">
                            <a href="mailto:hello@yoursite.com"><i class="lnr lnr-envelope"></i><span>admin@funfun.com</span></a>
                        </div>
                        <div class="info">
                            <p class="social pull-left">
                                <a class="no-margin" href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-google-plus"></i></a>
                                <a href="#"><i class="fa fa-pinterest"></i></a>
                                <a href="#"><i class="fa fa-linkedin"></i></a>
                                <a href="#"><i class="fa fa-dribbble"></i></a>
                            </p>
                        </div>
                    </div><!-- / contact-widget -->
                </div><!-- / widget -->
                <!-- / fourth widget -->

            </div><!-- / row -->
        </div><!-- / container -->
    </div><!-- / widget-area -->
    <div class="footer-info">
        <div class="container">
                <div class="pull-left">
                    <p>© 2016 - <strong>CloudFunFun</strong> - Responsive Shop Theme.</p>
                </div>
                <span class="pull-right">
                    <img src="${pageContext.request.contextPath}/resources/images/visa.png" alt="">
                    <img src="${pageContext.request.contextPath}/resources/images/mastercard.png" alt="">
                    <img src="${pageContext.request.contextPath}/resources/images/discover.png" alt="">
                    <img src="${pageContext.request.contextPath}/resources/images/paypal.png" alt="">
                </span>
        </div><!-- / container -->
    </div><!-- / footer-info -->
</footer>
</body>
</html>