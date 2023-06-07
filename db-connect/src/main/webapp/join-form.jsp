<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file = "include/header.jsp" %>
<body>
  <form action="join-process.jsp" method="post" class="join">
    <div class="container-sm mt-5">
      <div class="row justify-content-center">
        <div class="col-6">
          <div class="form-floating mb-3">
            <input type="text" name="userId" class="form-control" id="floatingInput" placeholder="아이디를 입력해 주세요." />
            <label for="floatingInput">ID</label>
          </div>
          <div class="form-floating mb-3">
            <input type="password" name="userPw" class="form-control" id="floatingPassword" placeholder="Password" />
            <label for="floatingPassword">Password</label>
          </div>
          <div class="form-floating mb-3">
            <input type="text" name="userName" class="form-control" id="floatingName" placeholder="이름을 입력해 주세요." />
            <label for="floatingName">Name</label>
          </div>
          <div class="input-group mb-3">
            <input type="text" class="form-control-lg" id="zonecode" placeholder="우편번호" name="zonecode" readonly />
            <button class="btn btn-secondary" type="button" id="button-addon2" onclick="searchZonecode()">Button</button>
          </div>
          <div class="form-floating mb-3">
            <input type="text" name="userAddress" class="form-control address" id="floatingAddress" placeholder="주소를 입력해 주세요." />
            <label for="floatingAddress">Address</label>
          </div>
          <div class="row mb-3">
            <div class="col">
              <input type="text" class="form-control-lg detailAddress" placeholder="상세주소" name="detailAddress" />
            </div>
            <div class="col">
              <input type="text" class="form-control-lg extraAddress" placeholder="참고사항" name="extraAddress" />
            </div>
          </div>
          <div class="text-center">
            <button type="submit" class="btn btn-primary btn-lg">Join</button>
          </div>
        </div>
      </div>
    </div>
  </form>
  <script>
    function searchZonecode() {
      new daum.Postcode({
        oncomplete: function (data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 각 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          let addr = ""; // 주소 변수
          let extraAddr = ""; // 참고항목 변수

          //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            addr = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              extraAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              extraAddr += extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraAddr !== "") {
              extraAddr = " (" + extraAddr + ")";
            }
            // 조합된 참고항목을 해당 필드에 넣는다.
            document.querySelector(".extraAddress").value = extraAddr;
          } else {
            document.querySelector(".extraAddress").value = "";
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.querySelector("#zonecode").value = data.zonecode;
          document.querySelector(".address").value = addr;
          // 커서를 상세주소 필드로 이동한다.
          document.querySelector(".detailAddress").focus();
        },
      }).open();
    }
  </script>
</body>
<%@ include file = "include/footer.jsp" %>
