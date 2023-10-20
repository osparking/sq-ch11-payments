package sq_ch11_payments.controller;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import space.jbpark.utility.MyUtil;
import sq_ch11_payments.dto.Payment;

@RestController
public class PaymentController {
	
	private static Logger logger =
			MyUtil.getLogger(PaymentController.class.getName());
	
	@PostMapping("/payment")
	public ResponseEntity<Payment> processPayment(
			@RequestHeader String requestId,
			@RequestBody Payment payment) {
		StringBuilder sb = new StringBuilder("요청ID: ");
		sb.append(requestId);
		sb.append("; 지불 금액: ");
		sb.append(payment.getAmount());
		logger.info(sb.toString());
		
		payment.setId(UUID.randomUUID().toString());
		return ResponseEntity
				.ok()
				.header("requestId", requestId)
				.body(payment);
	}

}
