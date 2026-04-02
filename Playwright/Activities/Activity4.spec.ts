import { expect, test } from "@playwright/test";

test("Activity 4", async ({ page }) => {
  // Go to the page
  await page.goto("https://training-support.net/webelements/drag-drop");

  // Get the title of the page
  const title = await page.title();
  console.log(`The title of the page is: ${title}`);

  // Get references to the elements
  const ball = page.getByTestId("ball");
  const dropzone1 = page.getByTestId("dropzone1");
  const dropzone2 = page.getByTestId("dropzone2");

  // Drag the ball into dropzone1
  await ball.dragTo(dropzone1);

  // Verify if dropzone1 was activated
  await expect(dropzone1).toHaveText(/Dropped/);

  // Drag the ball into dropzone2
  await ball.dragTo(dropzone2);

  // Verify if dropzone2 was activated
  await expect(dropzone2).toHaveText(/Dropped/);
});