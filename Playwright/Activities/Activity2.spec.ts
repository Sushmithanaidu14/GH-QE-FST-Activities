import { test } from "@playwright/test";

test("Activity 2", async ({ page }) => {
  // Go to the page
  await page.goto("https://training-support.net/webelements/simple-form");

  // Get the title of the page
  const title = await page.title();
  console.log(`The title of the page is: ${title}`);

  // Fill up the form
  await page.getByLabel("Full name").fill("Abhiram");
  await page.getByLabel("Email address").fill("abhiram@cklabs.com");
  await page.getByTestId("event-date").fill("2023-08-23");
  await page.getByLabel("Additional Details").fill("This is a party!!");
  await page.getByRole("button").filter({ hasText: "Submit" }).click();

  const result_text = page
    .getByRole("heading")
    .filter({ hasText: "scheduled!" });
  console.log(`The result text is: ${await result_text.textContent()}`);
});