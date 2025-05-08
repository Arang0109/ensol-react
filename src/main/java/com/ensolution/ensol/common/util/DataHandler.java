package com.ensolution.ensol.common.util;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.function.Consumer;

public class DataHandler {
  public static <T> void addOperationHandler(
      T dto,
      Consumer<T> service,
      RedirectAttributes rattr,
      String successMsg
  ) {
    try {
      service.accept(dto);
      setFlashAttributes(rattr, "success",
          successMsg + " added successfully", null);
    } catch (DuplicateKeyException e) {
      setFlashAttributes(rattr, "error", null, "already exist");
    }
  }

  private static void setFlashAttributes(RedirectAttributes rattr,
                                  String result, String successMsg, String errorMsg) {
    rattr.addFlashAttribute("result", result);
    if (successMsg != null) {
      rattr.addFlashAttribute("successMsg", successMsg);
    }
    if (errorMsg != null) {
      rattr.addFlashAttribute("errorMsg", errorMsg);
    }
  }
}
